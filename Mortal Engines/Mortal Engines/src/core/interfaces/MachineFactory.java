package ExamPreparation.FourteenAprilExam.src.core.interfaces;

import ExamPreparation.FourteenAprilExam.src.entities.interfaces.Fighter;
import ExamPreparation.FourteenAprilExam.src.entities.interfaces.Tank;

public interface MachineFactory {
    Tank createTank(String name, double attackPoints, double defensePoints);

    Fighter createFighter(String name, double attackPoints, double defensePoints);
}
