package ExamPreparation.FourteenAprilExam.src;

import ExamPreparation.FourteenAprilExam.src.core.Engine;
import ExamPreparation.FourteenAprilExam.src.core.MachineFactoryImpl;
import ExamPreparation.FourteenAprilExam.src.core.MachinesManagerImpl;

import ExamPreparation.FourteenAprilExam.src.core.PilotFactoryImpl;
import ExamPreparation.FourteenAprilExam.src.core.interfaces.MachineFactory;
import ExamPreparation.FourteenAprilExam.src.core.interfaces.PilotFactory;
import ExamPreparation.FourteenAprilExam.src.core.interfaces.MachinesManager;
import ExamPreparation.FourteenAprilExam.src.entities.interfaces.Machine;
import ExamPreparation.FourteenAprilExam.src.entities.interfaces.Pilot;


import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        PilotFactory pilotFactory = new PilotFactoryImpl();
        MachineFactory machineFactory = new MachineFactoryImpl();
        Map<String, Pilot> pilots = new LinkedHashMap<>();
        Map<String, Machine> machines = new LinkedHashMap<>();

        MachinesManager machinesManager = new MachinesManagerImpl(pilotFactory, machineFactory, pilots, machines);
        Engine engine = new Engine(machinesManager);
        try {
            engine.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
