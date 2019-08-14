package models.cards;

import common.ConstantMessages;
import models.cards.interfaces.Card;

public abstract class BaseCard implements Card {
    private static final int DAMAGE_POINTS_MIN = 0;
    private static final int HEALTH_POINTS_MIN = 0;

    private String name;
    private int damagePoints;
    private int healthPoints;

    protected BaseCard(String name, int damagePoints, int healthPoints){
        this.setName(name);
        this.setHealthPoints(healthPoints);
        this.setDamagePoints(damagePoints);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getDamagePoints() {
        return this.damagePoints;
    }

    @Override
    public void setDamagePoints(int damagePoints) {
        if(damagePoints < DAMAGE_POINTS_MIN){
            throw new IllegalArgumentException("Card's damage points cannot be less than zero.");
        }
        this.damagePoints = damagePoints;
    }

    @Override
    public int getHealthPoints() {
        return this.healthPoints;
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Card's name cannot be null or an empty string.");
        }
        this.name = name;
    }

    private void setHealthPoints(int healthPoints) {
        if(healthPoints < HEALTH_POINTS_MIN){
            throw new IllegalArgumentException("Card's HP cannot be less than zero.");
        }
        this.healthPoints = healthPoints;
    }

    @Override
    public String toString() {
        String result = String.format(ConstantMessages.CARD_REPORT_INFO, this.getName(), this.getDamagePoints());
        return result;
    }
}
