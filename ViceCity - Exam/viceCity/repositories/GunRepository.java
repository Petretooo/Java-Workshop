package repositories;

import models.guns.BaseGun;
import models.guns.Gun;
import repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GunRepository implements Repository<Gun> {

    Collection<Gun> models;

    public GunRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return this.models;
    }

    @Override
    public void add(Gun model) {
        if(!this.models.contains(model)){
            this.models.add(model);
        }
    }

    @Override
    public boolean remove(Gun model) {
        return this.models.remove(model);
    }

    @Override
    public Gun find(String name) {
        for (Gun model : models) {
            if(model.getName().equals(name)){
                return model;
            }
        }
        return null;
    }
}
