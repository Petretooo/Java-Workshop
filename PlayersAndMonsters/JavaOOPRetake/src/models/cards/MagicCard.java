package models.cards;

public class MagicCard extends BaseCard {
    private static final int DAMAGE_POINTS_DEFAULT = 5;
    private static final int HEALTH_POINTS_DEFAULT = 80;

    public MagicCard(String name) {
        super(name, DAMAGE_POINTS_DEFAULT, HEALTH_POINTS_DEFAULT);
    }


}
