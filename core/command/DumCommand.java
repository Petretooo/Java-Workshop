package WorkShop.core.command;

import WorkShop.core.system.DumpExtension;

public class DumCommand extends BaseCommand {


    private DumpExtension dumpExtension;

    @Override
    protected void parseArgs(Object... args) {
        this.dumpExtension = (DumpExtension) args[0];
    }

    @Override
    public void execute() {
        System.out.println("IT'S WORKING!");
    }
}
