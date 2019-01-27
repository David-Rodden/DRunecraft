package methods;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;

public class SimpleEarth extends DefaultWalkMethod {
    public SimpleEarth(final TreeScript handler) {
        super(handler, 557, "Earth");
        handler.addNotedPosition("bank", new Position(3254, 3423));
        handler.addNotedPosition("outside altar", new Position(3305, 3472));
        handler.addNotedPosition("inside altar", new Position(2657, 4835));
        buildTree(BankLocation.VARROCK_EAST);
    }
}
