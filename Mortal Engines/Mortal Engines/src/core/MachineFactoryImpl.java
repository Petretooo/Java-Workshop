package ExamPreparation.FourteenAprilExam.src.core;

import ExamPreparation.FourteenAprilExam.src.core.interfaces.MachineFactory;
import ExamPreparation.FourteenAprilExam.src.entities.FighterImpl;
import ExamPreparation.FourteenAprilExam.src.entities.TankImpl;
import ExamPreparation.FourteenAprilExam.src.entities.interfaces.Fighter;
import ExamPreparation.FourteenAprilExam.src.entities.interfaces.Tank;

public class MachineFactoryImpl implements MachineFactory {
    @Override
    public Tank createTank(String name, double attackPoints, double defensePoints) {
        return new TankImpl(name, attackPoints, defensePoints);
    }

    @Override
    public Fighter createFighter(String name, double attackPoints, double defensePoints) {
        return new FighterImpl(name, attackPoints, defensePoints);
    }
}
