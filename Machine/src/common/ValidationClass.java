package common;

import entities.interfaces.Pilot;

public class ValidationClass {

    public static boolean isValidName(String name){
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidPilot(Pilot pilot){
        return pilot != null;
    }

    public static boolean isValidTarget(String target) {
        return target != null && !target.isEmpty();
    }

}
