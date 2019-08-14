package WorkShop.core.system;

import WorkShop.core.model.hardware.Hardware;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class DumpExtension extends SystemSplit {

    private Map<String, Hardware> dumpHardwareCollection;


    private Map<String, Hardware> shareHardware;

    public DumpExtension() {
        this.dumpHardwareCollection = new LinkedHashMap<>();
        this.initialShareHardware();
    }

    private void initialShareHardware() {
        Class<?> systemSplitClass = this.getClass().getSuperclass();

        try {
            Field hardwareComponents = systemSplitClass.getDeclaredField("hardwareComponents");
            hardwareComponents.setAccessible(true);
            Field shareHardware = this.getClass().getDeclaredField("sharedHardware");
            shareHardware.setAccessible(true);
            shareHardware.set(this, hardwareComponents.get(this));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
