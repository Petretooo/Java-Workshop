import common.ConstantMessages;
import core.EngineImpl;
import core.interfaces.Controller;
import core.interfaces.ControllerImpl;
import core.interfaces.Engine;

public class Main {
    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
