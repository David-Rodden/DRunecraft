package methods;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;

public class SimpleWater extends DefaultWalkMethod {
    public SimpleWater(final TreeScript handler) {
        super(handler, 555, "Water");
        handler.addNotedPosition("bank", new Position(3207, 3218, 2));
        handler.addNotedPosition("outside altar", new Position(3183, 3165));
        handler.addNotedPosition("inside altar", new Position(2721, 4834));
    }
}
