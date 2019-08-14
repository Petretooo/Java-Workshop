package WorkShop.core.engine;

import WorkShop.core.command.Command;
import WorkShop.core.command.Factory.CommandFactory;
import WorkShop.core.reader.Reader;
import WorkShop.core.system.SystemSplit;

public class Engine {

    private static  final  String END_COMMAND = "System Split";

    private SystemSplit systemSplit;
    private Reader reader;

    public Engine(SystemSplit systemSplit, Reader reader) {
        this.systemSplit = systemSplit;
        this.reader = reader;
    }

    public void run(){
        String line = this.reader.readLine();
        while (END_COMMAND.equalsIgnoreCase(line)){
            String[] tokens = line.split("[\\(,\\s\\)]+");
            String commandName = tokens[0];
            Object[] params = new Object[tokens.length];
            for (int i = 1; i < tokens.length; i++) {
                params[i] = tokens[i];
            }
            Command command = CommandFactory.buildCommand(commandName, params);
            command.execute();
            line = this.reader.readLine();
        }
        Command command = CommandFactory.buildCommand("SystemSplit", this.systemSplit);
        command.execute();
    }
}
