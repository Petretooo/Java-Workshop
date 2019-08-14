package models.players;

import repositories.interfaces.CardRepository;

public class Advanced extends BasePlayer {

    private static final int HEALTH_POINTS_DEFAULT = 250;

    public Advanced(CardRepository cardRepository, String username) {
        super(cardRepository, username, HEALTH_POINTS_DEFAULT);
    }



}
