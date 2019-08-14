package ExamPreparation.FourteenAprilExam.src.core;

import ExamPreparation.FourteenAprilExam.src.common.OutputMessages;
import ExamPreparation.FourteenAprilExam.src.core.interfaces.MachineFactory;
import ExamPreparation.FourteenAprilExam.src.core.interfaces.PilotFactory;
import ExamPreparation.FourteenAprilExam.src.core.interfaces.MachinesManager;

import ExamPreparation.FourteenAprilExam.src.entities.interfaces.Fighter;
import ExamPreparation.FourteenAprilExam.src.entities.interfaces.Machine;
import ExamPreparation.FourteenAprilExam.src.entities.interfaces.Pilot;
import ExamPreparation.FourteenAprilExam.src.entities.interfaces.Tank;

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
        if(this.pilots.containsKey(name)){
            return String.format(OutputMessages.pilotExists, name);
        }else {
            this.addPilotToCollection(this.pilotFactory.createPilot(name));
            return String.format(OutputMessages.pilotHired, name);
        }
    }

    private void addPilotToCollection(Pilot pilot){
        this.pilots.put(pilot.getName(), pilot);
    }

    @Override
    public String manufactureTank(String name, double attackPoints, double defensePoints) {
        if(this.machines.containsKey(name)){
            return String.format(OutputMessages.machineExists, name);
        }else {
            this.addMachineToCollection(this.machineFactory.createTank(name,attackPoints,defensePoints));
            return String.format(OutputMessages.tankManufactured, name, attackPoints, defensePoints);
        }
    }

    private void addMachineToCollection(Machine machine){
        this.machines.put(machine.getName(), machine);
    }

    @Override
    public String manufactureFighter(String name, double attackPoints, double defensePoints) {
        if(this.machines.containsKey(name)){
            return String.format(OutputMessages.machineExists, name);
        }else {
            this.addMachineToCollection(this.machineFactory.createFighter(name, attackPoints, defensePoints));
            return String.format(OutputMessages.fighterManufactured, name, attackPoints, defensePoints);
        }
    }

    @Override
    public String engageMachine(String selectedPilotName, String selectedMachineName) {
        Pilot pilot = this.pilots.getOrDefault(selectedPilotName, null);
        Machine machine = this.machines.getOrDefault(selectedMachineName, null);
        String output;

        if(pilot == null){
            output = String.format(OutputMessages.pilotNotFound, selectedPilotName);
        }else if(machine == null){
            output = String.format(OutputMessages.machineNotFound, selectedMachineName);
        }else {
            if(machine.getPilot() != null){
                output = String.format(OutputMessages.machineHasPilotAlready, selectedMachineName);
            }else {
                pilot.addMachine(machine);
                machine.setPilot(pilot);
                output = String.format(OutputMessages.machineEngaged, selectedPilotName, selectedMachineName);
            }
        }

        return output;
    }

    @Override
    public String attackMachines(String attackingMachineName, String defendingMachineName) {
        Machine attacker = this.machines.getOrDefault(attackingMachineName, null);
        Machine defender = this.machines.getOrDefault(defendingMachineName, null);
        String output;

        if(attacker == null){
            output = String.format(OutputMessages.machineNotFound, attackingMachineName);
        }else if(defender == null){
            output = String.format(OutputMessages.machineNotFound, defendingMachineName);
        }else {
            double attacPoints = attacker.getAttackPoints();
            double defensePoints = defender.getDefensePoints();
            double health = defender.getHealthPoints();

            if(attacPoints> defensePoints){
                health -= (attacPoints - defensePoints);
                if(health < 0){
                    defender.setHealthPoints(0);
                    health = 0;
                }else {
                    defender.setHealthPoints(health);
                }
            }
            output = String.format(OutputMessages.attackSuccessful, defendingMachineName, attackingMachineName, health);
            attacker.attack(defendingMachineName);
        }

        return output;
    }

    @Override
    public String pilotReport(String pilotName) {
        if(!this.pilots.containsKey(pilotName)){
            return String.format(OutputMessages.pilotNotFound, pilotName);
        }else {
            Pilot pilot = this.pilots.get(pilotName);
            return pilot.report();
        }
    }

    @Override
    public String toggleFighterAggressiveMode(String fighterName) {
        try{
            Fighter fighter = (Fighter) this.machines.getOrDefault(fighterName, null);
            fighter.toggleAggressiveMode();
            return String.format(OutputMessages.fighterOperationSuccessful, fighterName);
        }catch (ClassCastException ex){
            return String.format(OutputMessages.notSupportedOperation, fighterName);
        }
    }

    @Override
    public String toggleTankDefenseMode(String tankName) {
        try{
            Tank tank = (Tank) this.machines.getOrDefault(tankName, null);
            tank.toggleDefenseMode();
            return String.format(OutputMessages.tankOperationSuccessful, tankName);
        }catch (ClassCastException ex){
            return String.format(OutputMessages.notSupportedOperation, tankName);
        }
    }
}
