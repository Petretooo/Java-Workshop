package barracksWars.core.commands;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

public class Fight extends Command {

    public Fight(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return this.getData()[0];
    }
}
