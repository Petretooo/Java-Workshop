package entities;

import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseMachine implements Machine {

    private String name;
    private Pilot pilot;
    private double attackPoints;
    private double defensePoints;
    private double healthPoints;
    private List<String> targets;

    protected BaseMachine(String name, double attackPoints, double defensePoints, double healthPoints) {
        this.setName(name);
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.healthPoints = healthPoints;
        this.targets = new ArrayList<>();
        this.pilot = null;
    }

    protected void setAttackPoints(double attackPoints) {
        this.attackPoints = attackPoints;
    }

    protected void setDefensePoints(double defensePoints) {
        this.defensePoints = defensePoints;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Machine name cannot be null or empty.");
        }
    }

    @Override
    public Pilot getPilot() {
        return this.pilot;
    }

    @Override
    public void setPilot(Pilot pilot) {
        if(pilot == null){
            throw new NullPointerException("Pilot cannot be null");
        }
        this.pilot = pilot;
    }

    @Override
    public double getHealthPoints() {
        return this.healthPoints;
    }

    @Override
    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    @Override
    public double getAttackPoints() {
        return this.attackPoints;
    }

    @Override
    public double getDefensePoints() {
        return this.defensePoints;
    }

    @Override
    public List<String> getTargets() {
        return this.targets;
    }

    @Override
    public void attack(String target) {
        if (target == null){
            throw new IllegalArgumentException("Attack target cannot be null or empty string.");
        }
        this.targets.add(target);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s", this.getName()))
                .append(System.lineSeparator());
        sb.append(String.format(" *Type: %s", this.getClass().getInterfaces()[0].getSimpleName()))
                .append(System.lineSeparator());
        sb.append(String.format(" *Health: %.2f", this.getHealthPoints()))
                .append(System.lineSeparator());
        sb.append(String.format(" *Attack: %.2f", this.getAttackPoints()))
                .append(System.lineSeparator());
        sb.append(String.format(" *Defense: %.2f", this.getDefensePoints()))
                .append(System.lineSeparator());
        sb.append(" *Targets:");

        if(this.getTargets().isEmpty()){
            sb.append("None")
                    .append(System.lineSeparator());
        }else {
            for (String target : this.getTargets()) {
                if(this.getTargets().indexOf(target) == this.getTargets().size()-1){
                    sb.append(target);
                    break;
                }
                sb.append(target).append(", ");
            }
        }

        return sb.toString().trim();

    }
}
