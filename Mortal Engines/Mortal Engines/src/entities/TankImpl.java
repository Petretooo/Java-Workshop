package ExamPreparation.FourteenAprilExam.src.entities;

import ExamPreparation.FourteenAprilExam.src.entities.interfaces.Tank;

public class TankImpl extends BaseMachine implements Tank {

    private static double DEFAULT_HEALTH_POINTS = 100;
    private static double ATTACK_POINTS_MODIFIER = 40;
    private static double DEFENSE_POINTS_MODIFIER = 30;
    private boolean defenseMode;

    public TankImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, DEFAULT_HEALTH_POINTS);
    }

    private void setDefenseModeTRUE(){
        this.defenseMode = true;
        super.setAttackPoints(super.getAttackPoints() - ATTACK_POINTS_MODIFIER);
        super.setDefensePoints(super.getDefensePoints() + DEFENSE_POINTS_MODIFIER);
    }

    private void setDefenseModeFALSE(){
        this.defenseMode = false;
        super.setAttackPoints(super.getAttackPoints() + ATTACK_POINTS_MODIFIER);
        super.setDefensePoints(super.getDefensePoints() - DEFENSE_POINTS_MODIFIER);
    }



    @Override
    public boolean getDefenseMode() {
        return this.defenseMode;
    }



    @Override
    public void toggleDefenseMode() {
        if(this.getDefenseMode()){
            setDefenseModeFALSE();
        }else {
            setDefenseModeTRUE();
        }
    }

    @Override
    public String toString() {
        String mode = this.getDefenseMode() ? "ON" : "OFF";
        String tankInfo = super.toString() + String.format(" *Defense Mode(%s)", mode);
        return tankInfo;

    }
}
