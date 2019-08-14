package entities;

import common.ValidationClass;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.ArrayList;
import java.util.List;

public class PilotImpl implements Pilot {

    private String name;
    private List<Machine> machines;

    public PilotImpl(String name) {
        this.name = name;
        this.machines = new ArrayList<>();
    }


    public void setName(String name) {
        if (!ValidationClass.isValidName(name)) {
            throw new IllegalArgumentException("Pilot name cannot be null or empty string.");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addMachine(Machine machine) {
        if (this.isValidMachine(machine)) {
            this.machines.add(machine);
        } else {
            throw new NullPointerException("Null machine cannot be added to the pilet");
        }
    }

    private boolean isValidMachine(Machine machine) {
        return machine != null;
    }

    @Override
    public List<Machine> getMachines() {

        return this.machines;

    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s - %d machines%n",
                this.getName(),
                this.getMachines().size()));


        for (Machine machine : this.machines) {
            sb.append(machine.toString())
                    .append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
