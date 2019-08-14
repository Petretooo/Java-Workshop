package motocrossWorldChampionship.repositories;

import  motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.repositories.interfaces.Repository;

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
        Motorcycle motorcycle = null;
        for (Motorcycle m : data) {
            if(m.getModel().equals(name)){
                motorcycle = m;
                break;
            }
        }
        return motorcycle;
    }

    @Override
    public Collection<Motorcycle> getAll() {
        return Collections.unmodifiableCollection(this.data);
    }

    @Override
    public void add(Motorcycle model) {
        this.data.add(model);
    }

    @Override
    public boolean remove(Motorcycle model) {
        return this.data.removeIf(m -> m.getModel().equals(model.getModel()));
    }
}
