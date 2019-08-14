package motocrossWorldChampionship.core;
import  motocrossWorldChampionship.common.ExceptionMessages;
import  motocrossWorldChampionship.common.OutputMessages;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import  motocrossWorldChampionship.entities.PowerMotorcycle;
import  motocrossWorldChampionship.entities.RaceImpl;
import  motocrossWorldChampionship.entities.RiderImpl;
import  motocrossWorldChampionship.entities.SpeedMotorcycle;
import  motocrossWorldChampionship.entities.interfaces.Motorcycle;
import  motocrossWorldChampionship.entities.interfaces.Race;
import  motocrossWorldChampionship.entities.interfaces.Rider;
import  motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ChampionshipControllerImpl implements ChampionshipController {

    private Repository<Motorcycle> motorcycleRepository;
    private Repository<Rider> riderRepository;
    private Repository<Race> raceRepository;

    public ChampionshipControllerImpl(
            Repository<Rider> riderRepository
            , Repository<Motorcycle> motorcycleRepository
            , Repository<Race> raceRepository
    ) {
        this.motorcycleRepository = motorcycleRepository;
        this.raceRepository = raceRepository;
        this.riderRepository = riderRepository;
    }

    @Override
    public String createRider(String riderName) {
        if (this.riderRepository.getByName(riderName) != null) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.RIDER_EXISTS, riderName)
            );
        }

        Rider rider = new RiderImpl(riderName);
        this.riderRepository.add(rider);
        return String.format(OutputMessages.RIDER_CREATED, riderName);
    }

    @Override
    public String createMotorcycle(String type, String model, int horsePower) {
        if (this.motorcycleRepository.getByName(model) != null) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.MOTORCYCLE_EXISTS, model)
            );
        }
        Motorcycle motorcycle = null;
        if (type.equals("Speed")) {
            motorcycle = new SpeedMotorcycle(model, horsePower);
        } else if (type.equals("Power")) {
            motorcycle = new PowerMotorcycle(model, horsePower);
        }

        this.motorcycleRepository.add(motorcycle);
        return String.format(OutputMessages.MOTORCYCLE_CREATED, motorcycle.getClass().getSimpleName(), model);
    }

    @Override
    public String addMotorcycleToRider(String riderName, String motorcycleModel) {
        Rider rider = this.riderRepository.getByName(riderName);
        Motorcycle motorcycle = this.motorcycleRepository.getByName(motorcycleModel);

        if (rider == null) {
            throw new NullPointerException(
                    String.format(ExceptionMessages.RIDER_NOT_FOUND, riderName)
            );
        } else if (motorcycle == null) {
            throw new NullPointerException(
                    String.format(ExceptionMessages.MOTORCYCLE_NOT_FOUND, motorcycleModel)
            );
        }

        rider.addMotorcycle(motorcycle);
        return String.format(OutputMessages.MOTORCYCLE_ADDED, riderName, motorcycleModel);
    }

    @Override
    public String addRiderToRace(String raceName, String riderName) {
        Race race = this.raceRepository.getByName(raceName);
        Rider rider = this.riderRepository.getByName(riderName);

        if (race == null) {
            throw new NullPointerException(
                    String.format(ExceptionMessages.RACE_NOT_FOUND, raceName)
            );
        } else if (rider == null) {
            throw new NullPointerException(
                    String.format(ExceptionMessages.RIDER_NOT_FOUND, riderName)
            );
        }

        race.addRider(rider);
        return String.format(OutputMessages.RIDER_ADDED, riderName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = this.raceRepository.getByName(raceName);

        if (race == null) {
            throw new NullPointerException(
                    String.format(ExceptionMessages.RACE_NOT_FOUND, raceName)
            );
        } else if (race.getRiders().size() < 3) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.RACE_INVALID, raceName, 3)
            );
        }

        List<Rider> winners = race
                .getRiders()
                .stream()
                .sorted((f, s) ->
                        Double.compare(
                                s.getMotorcycle().calculateRacePoints(race.getLaps()),
                                f.getMotorcycle().calculateRacePoints(race.getLaps()))
                ).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        winners.get(0).winRace();
        sb.append(String.format(OutputMessages.RIDER_FIRST_POSITION, winners.get(0).getName(), race.getName()))
                .append(System.lineSeparator());
        sb.append(String.format(OutputMessages.RIDER_SECOND_POSITION, winners.get(1).getName(), race.getName()))
                .append(System.lineSeparator());
        sb.append(String.format(OutputMessages.RIDER_THIRD_POSITION, winners.get(2).getName(), race.getName()))
                .append(System.lineSeparator());


        this.raceRepository.remove(race);
        return sb.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        if (this.raceRepository.getByName(name) != null) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.RACE_EXISTS, name)
            );
        }


        Race race = new RaceImpl(name, laps);
        this.raceRepository.add(race);
        return String.format(OutputMessages.RACE_CREATED, name);
    }
}
