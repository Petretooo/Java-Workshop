package repositories;

import entities.interfaces.Rider;
import repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RiderRepository implements Repository<Rider> {

    private Collection<Rider> data;

    public RiderRepository(){
        this.data = new ArrayList<>();
    }



    @Override
    public Rider getByName(String name) {
        Rider rider = null;
        for (Rider r : this.data) {
            if(r.getName().equals(name)){
                rider = r;
                break;
            }
        }
        return rider;
    }

    @Override
    public Collection<Rider> getAll() {
        return Collections.unmodifiableCollection(this.data);
    }

    @Override
    public void add(Rider rider) {
        this.data.add(rider);
    }

    @Override
    public boolean remove(Rider rider) {
        return this.data.removeIf(r -> r.getName().equals(rider.getName()));
    }
}
