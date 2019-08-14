package WorkShop.core.model.hardware;

import WorkShop.core.model.software.Software;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public abstract class Hardware {

    private String name;
    private Type type;
    private int maximumCapacity;
    private int maximumMemory;
    private List<Software> softwares;
    private int usedCapacity;
    private int usedMemory;

    public Hardware(String name, Type type, int maximumCapacity, int maximumMemory) {
        this.name = name;
        this.type = type;
        this.maximumCapacity = maximumCapacity;
        this.maximumMemory = maximumMemory;
        this.softwares = new ArrayList<>();
        this.usedCapacity = 0;
        this.usedMemory = 0;
    }

    public void addSoftware(Software software) {
        if (software.getCapacityConsumption() <= this.getMaximumMemory()
                && software.getCapacityConsumption() <= this.getMaximumCapacity()) {
            this.softwares.add(software);
            this.setUsedResources(software, (a, b) -> a += b);
        }
    }

    public final int getUsedCapacity() {
        return usedCapacity;
    }

    public final int getUsedMemory() {
        return usedMemory;
    }

    private void setUsedResources(Software software, BinaryOperator<Integer> operator) {
        this.usedCapacity = operator.apply(this.usedCapacity, software.getCapacityConsumption());
        this.usedMemory = operator.apply(this.usedMemory, software.getMemoryConsumption());
        this.usedCapacity += software.getCapacityConsumption();
        this.usedMemory += software.getMemoryConsumption();
    }


    public int getSoftwareCount() {
        return this.softwares.size();
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type.name();
    }

    protected final List<Software> getSoftwares() {
        return Collections.unmodifiableList(softwares);
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public int getMaximumMemory() {
        return maximumMemory;
    }

    public void removeSoftware(String softwareName) {
        Software software = this.softwares.stream()
                .filter(s -> s.getName().equalsIgnoreCase(softwareName))
                .findFirst()
                .orElse(null);
        if (software != null) {
            this.softwares.remove(software);
            this.setUsedResources(software, (a, b) -> a -= b);
        }

    }

    @Override
    public String toString() {
        return this.softwares.size() == 0 ? "None" : this.softwares.stream()
                .map(Software::getName).collect(Collectors.joining(", "));
    }

    protected enum Type {
        POWER, HEAVY;

    }

}
