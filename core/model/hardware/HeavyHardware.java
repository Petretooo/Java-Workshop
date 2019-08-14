package WorkShop.core.model.hardware;

import WorkShop.core.model.software.Software;

import java.util.stream.Collectors;

public class HeavyHardware extends Hardware {

    public HeavyHardware(String name, int maximumCapacity, int maximumMemory) {
        super(name, Type.HEAVY, maximumCapacity, maximumMemory);
    }

    @Override
    public int getMaximumCapacity() {
        int baseCapacity = super.getMaximumCapacity();
        baseCapacity *= 2;
        return baseCapacity - super.getUsedCapacity();
    }

    @Override
    public int getMaximumMemory() {
        int baseMemory = super.getMaximumMemory();
        baseMemory -= baseMemory / 4;
        return baseMemory - super.getUsedMemory();
    }

    @Override
    public String toString() {

        long expressSoftwareCount = this.getSoftwares().stream()
                .filter(s -> s.getClass().getSimpleName().equals("ExpressSoftware"))
                .count();
        return String.format("Hardware Component - %s\n" +
                        "Express Software Components - %d\n" +
                        "Light Software Components - %d\n" +
                        "Memory Usage: %d / %d\n" +
                        "Capacity Usage: %d / %d\n" +
                        "Type: %s\n" +
                        "Software Components: %s\n", this.getName(), expressSoftwareCount,
                this.getSoftwares().size() - expressSoftwareCount, this.getUsedMemory(),
                this.getMaximumMemory(),
                this.getMaximumMemory() + this.getUsedMemory(),
                this.getUsedCapacity(), this.getUsedCapacity() + this.getUsedCapacity(),
                this.getType().compareTo(Type.HEAVY.name()) == 0 ? "Heavy" : "Power"
                , super.toString());
    }
}
