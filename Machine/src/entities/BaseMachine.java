package entities;

import common.ValidationClass;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMachine implements Machine {

    private String name;
    private Pilot pilot;
    private double attackPoints;
    private double defensePoints;
    private double healthPoints;
    private List<String> targets;

    public BaseMachine(String name, double attackPoints, double defensePoints, double healthPoints) {
        this.name = name;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.healthPoints = healthPoints;
        this.targets = new ArrayList<>();
        this.pilot = null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if(!ValidationClass.isValidName(name)){
            throw new IllegalArgumentException("Machine name cannot be null or empty");
        }
        this.name = name;
    }

    @Override
    public Pilot getPilot() {
        return this.pilot;
    }

    @Override
    public void setPilot(Pilot pilot) {
        if(!ValidationClass.isValidPilot(pilot)){
            throw new NullPointerException("Pilot cannot be null");
        }
    }

    @Override
    public double getHealthPoints() {
        return this.healthPoints;
    }

    @Override
    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setAttackPoints(double attackPoints) {
        this.attackPoints = attackPoints;
    }

    public void setDefensePoints(double defensePoints) {
        this.defensePoints = defensePoints;
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
        if(ValidationClass.isValidTarget(target)){
            this.targets.add(target);
        }else {
            throw new IllegalArgumentException("Attack target cannot be null or empty string.");
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(String.format("- %s%n", this.getName()))
                .append(String.format(" *Type: %s.%n", this.getClass().getInterfaces()[0].getSimpleName()))
                .append(String.format(" *Health %.2f%n", this.getHealthPoints()))
                .append(String.format(" *Attacl %.2f%n", this.getAttackPoints()))
                .append(String.format(" *Defense: %.2f%n", this.getDefensePoints()))
                .append(" *Targets: ");

        if(this.getTargets().isEmpty()){
            sb.append("None").append(System.lineSeparator());
        }else {
            for (String target : this.getTargets()) {
                sb.append(target).append(", ");
            }
            sb
                    .deleteCharAt(sb.length()-1)
                    .deleteCharAt(sb.length()-1)
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
