package core;

import core.interfaces.PilotFactory;
import entities.PilotImpl;
import entities.interfaces.Pilot;

public class PilotFactoryImpl implements PilotFactory {
    public PilotFactoryImpl() {
    }

    @Override
    public Pilot createPilot(String name) {
        return new PilotImpl(name);
    }
}
