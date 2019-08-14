package models.guns;

public class Pistol extends BaseGun {

    private static final int BULLETS_PER_BARREL = 10;
    private static final int TOTAL_BULLETS = 100;
    private int bullets = BULLETS_PER_BARREL;
    private int total = TOTAL_BULLETS;
    private int bulletCount;
    public Pistol(String name) {
        super(name, 10, 100);
        bulletCount = 0;
    }

    @Override
    public int fire() {
        this.bullets -= 1;
        bulletCount++;
        if(this.bullets == 0 && this.total != 0){
            this.bullets += 10;
            this.total -= 10;
        }
        return bulletCount;
    }
}
