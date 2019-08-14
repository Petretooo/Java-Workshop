package models.players;

import repositories.interfaces.CardRepository;

public class Beginner extends BasePlayer {

    private static final int HEALTH_POINTS_DEFAULT = 50;

    public Beginner(CardRepository cardRepository, String username) {
        super(cardRepository, username, HEALTH_POINTS_DEFAULT);
    }



}
