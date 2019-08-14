package entities;

import common.ExceptionMessages;

public class SpeedMotorcycle extends MotorcycleImpl {
    private static final double CUBIC_CENTIMETERS = 125;


    public SpeedMotorcycle(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
    }

    @Override
    public void setHorsePower(int horsePower) {
        if(horsePower < 50 || horsePower > 69){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }
        super.setHorsePower(horsePower);
    }
}
