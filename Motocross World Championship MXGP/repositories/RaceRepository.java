package repositories;

import entities.interfaces.Race;
import repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RaceRepository implements Repository<Race> {
    private Collection<Race> data;

    public RaceRepository(){
        this.data = new ArrayList<>();
    }



    @Override
    public Race getByName(String name) {
        return this.data.stream()
                .filter(entry -> entry.getName().equals(name))
                .limit(1)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(this.data);
    }

    @Override
    public void add(Race race) {
        this.data.add(race);
    }

    @Override
    public boolean remove(Race race) {
        return this.data.removeIf(r -> r.getName().equals(race.getName()));
    }
}
