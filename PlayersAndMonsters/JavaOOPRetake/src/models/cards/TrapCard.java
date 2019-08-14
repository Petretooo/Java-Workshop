package models.cards;

public class TrapCard extends BaseCard {
    private static final int DAMAGE_POINTS_DEFAULT = 120;
    private static final int HEALTH_POINTS_DEFAULT = 5;

    public TrapCard(String name) {
        super(name, DAMAGE_POINTS_DEFAULT, HEALTH_POINTS_DEFAULT);
    }

}
