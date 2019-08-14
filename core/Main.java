package WorkShop.core;

import WorkShop.core.engine.Engine;
import WorkShop.core.reader.ConsoleReader;
import WorkShop.core.reader.Reader;
import WorkShop.core.system.DumpExtension;
import WorkShop.core.system.SystemSplit;

public class Main {
    public static void main(String[] args) {
        Reader reader = new ConsoleReader();
        SystemSplit systemSplit = new DumpExtension();
        Engine engine = new Engine(systemSplit, reader);
        engine.run();
    }
}
