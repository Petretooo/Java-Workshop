package motocrossWorldChampionship.entities;

import  motocrossWorldChampionship.common.ExceptionMessages;

public class PowerMotorcycle extends MotorcycleImpl {

    private static final int CUBIC_CENTIMETERS_DEFAULT = 450;

    public PowerMotorcycle(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS_DEFAULT);
    }

    @Override
    protected void setHorsePower(int horsePower) {
        if(horsePower < 70 || horsePower > 100){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }
        super.setHorsePower(horsePower);
    }
}
