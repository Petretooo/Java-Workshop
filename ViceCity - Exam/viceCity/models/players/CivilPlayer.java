package models.players;


public class CivilPlayer extends BasePlayer {

    private static final int DEFAULT_INITIAL_LIFE_POINTS = 100;

    public CivilPlayer(String name) {
        super(name, DEFAULT_INITIAL_LIFE_POINTS);
    }

    @Override
    public void takeLifePoints(int points) {
        super.takeLifePoints(points);
    }
}
