package models.players;


public class MainPlayer extends BasePlayer {

    private static final int DEFAULT_INITIAL_LIFE_POINTS = 100;
    private static final String NAME= "Tommy Vercetti";

    public MainPlayer() {
        super(NAME, DEFAULT_INITIAL_LIFE_POINTS);
    }

    @Override
    public void takeLifePoints(int points) {
        super.takeLifePoints(points);
    }
}
