package common;

import java.util.Arrays;
import java.util.List;

public class InputInterpreter {
    public InputInterpreter() {
    }

    public List<String> getCommandArguments(String line) {
        String[] lineSplit = line.split(" ");
        List<String> commandArgs = Arrays.asList(
                Arrays.copyOfRange(lineSplit, 1, lineSplit.length));
        return commandArgs;
    }

    public String getCommandName(String line) {
        return line.split(" ")[0];
    }

}