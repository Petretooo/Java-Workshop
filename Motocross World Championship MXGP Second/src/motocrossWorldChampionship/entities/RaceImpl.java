package motocrossWorldChampionship.entities;

import  motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;

import java.util.ArrayList;
import java.util.Collection;

public class RaceImpl implements Race {

    private String name;
    private int laps;
    private Collection<Rider> riders;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.riders = new ArrayList<>();
    }

    private void setName(String name) {
        if(name == null || name.trim().length() < 5){
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.INVALID_NAME, name, 5)
            );
        }
        this.name = name;
    }

    private void setLaps(int laps) {
        if(laps < 1){
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.INVALID_NUMBER_OF_LAPS, 1)
            );
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Rider> getRiders() {
        return this.riders;
    }

    @Override
    public void addRider(Rider rider) {
        if(rider == null){
            throw new NullPointerException(ExceptionMessages.RIDER_INVALID);
        }else if(!rider.getCanParticipate()){
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.RIDER_NOT_PARTICIPATE, rider.getName())
            );
        }

        for (Rider rider1 : riders) {
            if(rider1.getName().equals(rider.getName())){
                throw new IllegalArgumentException(
                        String.format(ExceptionMessages.RIDER_ALREADY_ADDED, rider.getName(), this.getName())
                );
            }
        }

        this.riders.add(rider);

    }

}
