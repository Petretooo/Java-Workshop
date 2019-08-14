package motocrossWorldChampionship.entities;

import  motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;

public abstract class MotorcycleImpl implements Motorcycle {

    private String model;
    private int horsePower;
    private double cubicCentimeters;

    protected MotorcycleImpl(String model, int horsePower, double cubicCentimeters) {
        this.setModel(model);
        this.setHorsePower(horsePower);
        this.cubicCentimeters = cubicCentimeters;
    }

    private void setModel(String model) {
        if(model == null || model.trim().length() < 4){
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.INVALID_MODEL, model, 4)
            );
        }
        this.model = model;
    }

    protected void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return this.getCubicCentimeters() / this.getHorsePower() * laps;
    }
}
