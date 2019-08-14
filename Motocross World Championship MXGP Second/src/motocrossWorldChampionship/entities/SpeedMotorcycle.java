package motocrossWorldChampionship.entities;

import  motocrossWorldChampionship.common.ExceptionMessages;

public class SpeedMotorcycle extends MotorcycleImpl {
    private static final int CUBIC_CENTIMETERS_DEFAULT = 125;

    public SpeedMotorcycle(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS_DEFAULT);
    }

    @Override
    protected void setHorsePower(int horsePower) {
        if(horsePower < 50 || horsePower > 69){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }
        super.setHorsePower(horsePower);
    }
}
