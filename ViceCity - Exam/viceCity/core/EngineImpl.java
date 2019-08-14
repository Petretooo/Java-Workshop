package core;

import common.Command;
import core.interfaces.Controller;
import core.interfaces.Engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EngineImpl implements Engine {
    private Controller controller;
    private BufferedReader reader;

    public EngineImpl(Controller controller) {
        this.controller = controller;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();

                if (result.equals(Command.Exit.name())) {
                    break;
                }
            } catch (NullPointerException | IllegalArgumentException | IOException e) {
                result = e.getMessage();
            }

            System.out.println(result);
        }
    }

    private String processInput() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s+");

        Command command = Command.valueOf(tokens[0]);
        String result = null;
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        switch (command) {
            case AddPlayer:
                result = addPlayerCard(data);
                break;
            case AddGun:
                result = addGun(data);
                break;
            case AddGunToPlayer:
                result = addGunTo(data);
                break;
            case Fight:
                result = fight(data);
                break;
            case Exit:
                result = "Exit";
                break;
        }

        return result;
    }

    private String addPlayerCard(String[] data) {
        String username = data[0];

        String result = this.controller.addPlayer(username);
        return result;
    }
    private String addGun(String[] data) {
        String username = data[0];
        String gun = data[1];

        String result = this.controller.addGun(username, gun);
        return result;
    }

    private String addGunTo(String[] data) {
        String username = data[0];

        String result = this.controller.addGunToPlayer(username);
        return result;
    }

    private String fight(String[] data) {
        this.controller.fight();

        String result = "Fight";
        return result;
    }
}
