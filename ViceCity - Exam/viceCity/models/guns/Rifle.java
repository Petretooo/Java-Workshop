package models.guns;

public class Rifle extends BaseGun {
    private static final int BULLETS_PER_BARREL = 50;
    private static final int TOTAL_BULLETS = 500;
    private int bullets = BULLETS_PER_BARREL;
    private int total = TOTAL_BULLETS;
    private int bulletCount;

    public Rifle(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
        this.bulletCount = 0;
    }

    @Override
    public int fire() {
        this.bullets -= 5;
        bulletCount+=5;
        if(this.bullets == 0 && this.total != 0){
            this.bullets += 50;
            this.total -= 50;
        }
        return bulletCount;
    }
}
