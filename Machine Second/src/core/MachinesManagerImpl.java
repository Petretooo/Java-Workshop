package core;

import common.OutputMessages;
import core.interfaces.MachineFactory;
import core.interfaces.PilotFactory;
import core.interfaces.MachinesManager;

import entities.interfaces.Fighter;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;
import entities.interfaces.Tank;

import java.util.Map;

public class MachinesManagerImpl implements MachinesManager {

    private PilotFactory pilotFactory;
    private MachineFactory machineFactory;
    private Map<String, Pilot> pilots;
    private Map<String, Machine> machines;

    public MachinesManagerImpl(PilotFactory pilotFactory, MachineFactory machineFactory, Map<String, Pilot> pilots, Map<String, Machine> machines) {
        this.pilotFactory = pilotFactory;
        this.machineFactory = machineFactory;
        this.pilots = pilots;
        this.machines = machines;
    }

    @Override
    public String hirePilot(String name) {
        if (isExistingPilot(name)) {
            return String.format(OutputMessages.pilotExists, name);
        } else {
            this.addPilotToCollection(this.pilotFactory.createPilot(name));
            return String.format(OutputMessages.pilotHired, name);
        }
    }

    private void addPilotToCollection(Pilot pilot) {
        this.pilots.put(pilot.getName(), pilot);
    }

    private void addMachineToCollection(Machine machine) {
        this.machines.put(machine.getName(), machine);
    }

    @Override
    public String manufactureTank(String name, double attackPoints, double defensePoints) {
        if (isExistingMachine(name)) {
            return String.format(OutputMessages.machineExists, name);
        } else {
            this.addMachineToCollection(this.machineFactory.createTank(name, attackPoints, defensePoints));
            return String.format(OutputMessages.tankManufactured, name, attackPoints, defensePoints);
        }
    }

    @Override
    public String manufactureFighter(String name, double attackPoints, double defensePoints) {
        if (isExistingMachine(name)) {
            return String.format(OutputMessages.machineExists, name);
        } else {
            this.addMachineToCollection(this.machineFactory.createFighter(name, attackPoints, defensePoints));
            return String.format(OutputMessages.fighterManufactured, name, attackPoints, defensePoints);
        }
    }

    @Override
    public String engageMachine(String selectedPilotName, String selectedMachineName) {
        Pilot pilot = this.getPilotByName(selectedPilotName);
        Machine machine = this.getMachineByName(selectedMachineName);
        String output;

        if (pilot == null) {
            output = String.format(OutputMessages.pilotNotFound, selectedPilotName);
        } else if (machine == null) {
            output = String.format(OutputMessages.machineNotFound, selectedMachineName);
        } else {
            if (machine.getPilot() != null) {
                output = String.format(OutputMessages.machineHasPilotAlready, selectedMachineName);
            } else {
                pilot.addMachine(machine);
                machine.setPilot(pilot);

                output = String.format(OutputMessages.machineEngaged, selectedPilotName, selectedMachineName);
            }
        }
        return output;
    }

    @Override
    public String attackMachines(String attackingMachineName, String defendingMachineName) {
        Machine attacker = this.getMachineByName(attackingMachineName);
        Machine defender = this.getMachineByName(defendingMachineName);
        String output;

        if (attacker == null) {
            output = String.format(OutputMessages.machineNotFound, attackingMachineName);
        } else if (defender == null) {
            output = String.format(OutputMessages.machineNotFound, defendingMachineName);
        } else {
            double attackPoints = attacker.getAttackPoints();
            double defensePoints = defender.getDefensePoints();
            double healthPool = defender.getHealthPoints();

            if (attackPoints > defensePoints) {
                healthPool -= (attackPoints - defensePoints);
                if (healthPool < 0) {
                    defender.setHealthPoints(0);
                    healthPool = 0;
                } else {
                    defender.setHealthPoints(healthPool);
                }
            }
            output = String.format(OutputMessages.attackSuccessful, defendingMachineName, attackingMachineName, healthPool);
            attacker.attack(defendingMachineName);
        }
        return output;
    }

    @Override
    public String pilotReport(String pilotName) {
        String output;

        if (!this.isExistingPilot(pilotName)) {
            output = String.format(OutputMessages.pilotNotFound, pilotName);
        } else {
            Pilot pilot = this.pilots.get(pilotName);
            output = pilot.report();
        }
        return output;
    }

    @Override
    public String toggleFighterAggressiveMode(String fighterName) {
        String output;
        try {
            Fighter fighter = (Fighter) this.getMachineByName(fighterName);
            fighter.toggleAggressiveMode();
            output = String.format(OutputMessages.fighterOperationSuccessful, fighterName);
        } catch (ClassCastException e) {
            output = String.format(OutputMessages.notSupportedOperation, fighterName);
        }
        return output;
    }

    @Override
    public String toggleTankDefenseMode(String tankName) {
        String output;
        try {
            Tank tank = (Tank) this.getMachineByName(tankName);
            tank.toggleDefenseMode();
            output = String.format(OutputMessages.tankOperationSuccessful, tank.getName());
        } catch (ClassCastException e) {
            output = String.format(OutputMessages.notSupportedOperation, tankName);
        }
        return output;
    }

    private boolean isExistingMachine(String machineName) {
        return this.machines.containsKey(machineName);
    }

    private boolean isExistingPilot(String pilotName) {
        return this.pilots.containsKey(pilotName);
    }

    private Pilot getPilotByName(String pilotName) {
        return this.pilots.getOrDefault(pilotName, null);
    }

    private Machine getMachineByName(String machineName) {
        return this.machines.getOrDefault(machineName, null);
    }
}
