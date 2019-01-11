package methods;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;

public class SimpleBody extends DefaultWalkMethod {
    public SimpleBody(final TreeScript handler) {
        super(handler, 559, "Body");
        // TODO: 1/10/2019 Get correct coordinates for edgeville bank, and outside/inside altar
        handler.addNotedPosition("bank", new Position(3091, 3491));
        handler.addNotedPosition("outside altar", new Position(3055, 3443));
        handler.addNotedPosition("inside altar", new Position(2521, 4844));
    }
}
