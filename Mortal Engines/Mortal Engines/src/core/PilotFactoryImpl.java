package ExamPreparation.FourteenAprilExam.src.core;

import ExamPreparation.FourteenAprilExam.src.core.interfaces.PilotFactory;
import ExamPreparation.FourteenAprilExam.src.entities.PilotImpl;
import ExamPreparation.FourteenAprilExam.src.entities.interfaces.Pilot;

public class PilotFactoryImpl implements PilotFactory {

    public PilotFactoryImpl() {
    }

    @Override
    public Pilot createPilot(String name) {
        return new PilotImpl(name);
    }
}
