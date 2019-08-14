package WorkShop.core.system;

import WorkShop.core.model.hardware.Hardware;
import WorkShop.core.model.software.Software;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.ToIntFunction;

public class SystemSplit {

    private Map<String, Hardware> hardwareComponents;

    public SystemSplit() {
        this.hardwareComponents = new LinkedHashMap<>();
    }

    public void addHardwareComponent(Hardware hardware) {
        this.hardwareComponents.put(hardware.getName(), hardware);
    }

    public void addSoftwareToHardware(String hardwareName, Software software) {
        if (this.hardwareComponents.containsKey(hardwareName)) {
            Hardware hardware = this.hardwareComponents.get(hardwareName);
            hardware.addSoftware(software);

        }


    }


    public int getHardwareComponentSize() {
        return this.hardwareComponents.size();
    }

    public int getSoftwareComponentCount() {
        return this.getSumValue(Hardware::getSoftwareCount);
    }


    public int getTotalOperationalMemoryInUse() {
        return this.getSumValue(Hardware::getUsedMemory);
    }

    public int getTotalCapacityTaken() {
        return this.getSumValue(Hardware::getUsedCapacity);
    }

    public int getMaximumMemory() {
        return this.getSumValue(Hardware::getMaximumMemory);
    }

    public int getMaximumCapacity() {
        return this.getSumValue(Hardware::getMaximumCapacity);
    }

    private int getSumValue(ToIntFunction<Hardware> func) {
        return this.hardwareComponents
                .values()
                .stream()
                .mapToInt(func)
                .sum();
    }

    public void releaseSoftware(String hardwareName, String softwareName) {
        if (this.hardwareComponents.containsKey(hardwareName)) {
            Hardware hardware = this.hardwareComponents.get(hardwareName);
            hardware.removeSoftware(softwareName);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.hardwareComponents.values()
                .stream()
                .sorted((h1, h2) -> {
                    if(h1.getType().equalsIgnoreCase("Power")){
                        return -1;
                    }else {
                        return 1;
                    }

        }).forEach(h -> {
            sb.append(h.toString());
            sb.append(System.lineSeparator());
        });
        return sb.toString().trim();
    }
}
