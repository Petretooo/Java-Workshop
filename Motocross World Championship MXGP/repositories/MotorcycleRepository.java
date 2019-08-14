package repositories;

import entities.interfaces.Motorcycle;
import repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MotorcycleRepository implements Repository<Motorcycle> {
    private Collection<Motorcycle> data;

    public MotorcycleRepository(){
        this.data = new ArrayList<>();
    }


    @Override
    public Motorcycle getByName(String name) {
        return this.data.stream()
                .filter(entry -> entry.getModel().equals(name)).limit(1)
                .findFirst().orElse(null);
    }

    @Override
    public Collection<Motorcycle> getAll() {
        return Collections.unmodifiableCollection(this.data);
    }

    @Override
    public void add(Motorcycle motorcycle) {
        this.data.add(motorcycle);
    }

    @Override
    public boolean remove(Motorcycle motorcycle) {
        return this.data.removeIf(m -> m.getModel().equals(motorcycle.getModel()));
    }
}
