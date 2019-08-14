package models.players;

import common.ConstantMessages;
import models.players.interfaces.Player;
import repositories.interfaces.CardRepository;

public abstract class BasePlayer implements Player {

    private static final int HEALTH_POINTS_MIN = 0;
    private static final int DAMAGE_POINTS_MIN = 0;


    private String username;
    private int health;
    private CardRepository cardRepository;
    private boolean isDead;

    protected BasePlayer(CardRepository cardRepository, String username, int health) {
        this.cardRepository = cardRepository;
        this.setUsername(username);
        this.setHealth(health);
        this.setDead(false);
    }


    @Override
    public CardRepository getCardRepository() {
        return this.cardRepository;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int healthPoints) {
        if(healthPoints < HEALTH_POINTS_MIN){
            throw new IllegalArgumentException("Player's health bonus cannot be less than zero.");
        }
        this.health = healthPoints;
    }

    @Override
    public boolean isDead() {
        return this.isDead;
    }

    @Override
    public void takeDamage(int damagePoints) {
        if(damagePoints < DAMAGE_POINTS_MIN){
            throw new IllegalArgumentException("Damage points cannot be less than zero.");
        }

        this.health -= damagePoints;

        if(this.health <= HEALTH_POINTS_MIN){
            this.health = HEALTH_POINTS_MIN;
            this.isDead = true;
        }

    }

    private void setUsername(String username) {
        if(username == null || username.trim().isEmpty()){
            throw new IllegalArgumentException("Player's username cannot be null or an empty string.");
        }
        this.username = username;
    }

    private void setDead(boolean dead) {
        this.isDead = dead;
    }


    @Override
    public String toString() {
        int cardCount = this.cardRepository.getCount();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.PLAYER_REPORT_INFO, this.getUsername(), this.getHealth(), cardCount))
                .append(System.lineSeparator());

        this.getCardRepository()
                .getCards()
                .forEach(c -> sb.append(c.toString())
                        .append(System.lineSeparator()));

        sb.append(ConstantMessages.DEFAULT_REPORT_SEPARATOR);
        return sb.toString().trim();
    }
}
