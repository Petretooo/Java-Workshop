package barracksWars.core.commands;

import barracksWars.anotation.Inject;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

public class Retire extends Command {
    @Inject
    private Repository repository;

    public Retire(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = this.getData()[1];
        String result;
        try {
            this.repository.removeUnit(unitType);
            result =unitType  + " retired!";
        } catch (IllegalArgumentException e) {
            result = e.getMessage();
        }
        return result;
    }
}
