package entities;

import entities.interfaces.Tank;

public class TankImpl extends BaseMachine implements Tank {
    private static final double DEFAULT_HEALTH_POINTS = 200.0;
    private static final double ATTACK_POINTS_MODIFIER = 50.0;
    private static final double DEFENSE_POINTS_MODIFIER = 25.0;
    private boolean aggressiveMode;

    public TankImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, DEFAULT_HEALTH_POINTS);
        this.setAggressiveModeTRUE();
    }


    private void setAggressiveModeTRUE() {
        this.aggressiveMode = true;
        super.setAttackPoints(super.getAttackPoints() + ATTACK_POINTS_MODIFIER);
        super.setDefensePoints(super.getDefensePoints() - DEFENSE_POINTS_MODIFIER);
    }

    private void setAggressiveModeFALSE(){
        this.aggressiveMode = false;
        super.setAttackPoints(super.getAttackPoints() - ATTACK_POINTS_MODIFIER);
        super.setDefensePoints(super.getDefensePoints() + DEFENSE_POINTS_MODIFIER);
    }

    @Override
    public boolean getDefenseMode() {
        return this.aggressiveMode;
    }

    @Override
    public void toggleDefenseMode() {
        if(this.getDefenseMode()){
            this.setAggressiveModeFALSE();
        }else {
            this.setAggressiveModeTRUE();
        }
    }

    @Override
    public String toString() {
        String mode = this.getDefenseMode() ? "ON" : "OFF";
        String tankInfo = super.toString() + String.format(" *Defense mode(%s)", mode);
        return tankInfo;
    }
}
