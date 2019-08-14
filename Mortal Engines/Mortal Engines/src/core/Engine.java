package ExamPreparation.FourteenAprilExam.src.core;

import ExamPreparation.FourteenAprilExam.src.common.InputInterpreter;
import ExamPreparation.FourteenAprilExam.src.core.interfaces.MachinesManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Engine {
    private static final String END_PROGRAM_COMMAND = "Over";
    private MachinesManager machinesManager;
    private InputInterpreter inputInterpreter;
    private BufferedReader reader;

    public Engine(MachinesManager machinesManager) {
        this.machinesManager = machinesManager;
        this.inputInterpreter = new InputInterpreter();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() throws IOException {
        String input = this.reader.readLine();

        while (!input.equals(END_PROGRAM_COMMAND)) {
            List<String> commandArgs = this.inputInterpreter.getCommandArguments(input);
            String commandName = this.inputInterpreter.getCommandName(input);

            String output = this.handleCommand(commandName, commandArgs);
            System.out.println(output);
            input = this.reader.readLine();
        }

    }

    private String handleCommand(String commandName, List<String> arguments) {
        String name = arguments.get(0);
        double attack, defense;
        String output = "";

        switch (commandName) {
            case "Hire":

                output = this.machinesManager.hirePilot(name);
                break;
            case "ManufactureTank":

                attack = Double.parseDouble(arguments.get(1));
                defense = Double.parseDouble(arguments.get(2));
                output = this.machinesManager.manufactureTank(name, attack, defense);
                break;
            case "ManufactureFighter":

                attack = Double.parseDouble(arguments.get(1));
                defense = Double.parseDouble(arguments.get(2));
                output = this.machinesManager.manufactureFighter(name, attack, defense);
                break;
            case "Engage":
                String machineName = arguments.get(1);
                output = this.machinesManager.engageMachine(name, machineName);
                break;
            case "Attack":
                String attackerName = arguments.get(1);
                output = this.machinesManager.attackMachines(name, attackerName);
                break;
            case "DefenseMode":
                output = this.machinesManager.toggleTankDefenseMode(name);
                break;
            case "AggressiveMode":
                output = this.machinesManager.toggleFighterAggressiveMode(name);
                break;
            case "Report":
                output = this.machinesManager.pilotReport(name);
                break;
        }
        return output;
    }
}
