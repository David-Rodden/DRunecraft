package methods;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;

public class SimpleFire extends DefaultWalkMethod {

    public SimpleFire(final TreeScript handler) {
        super(handler, 554, "Fire");
        handler.addNotedPosition("bank", new Position(3382, 3267));
        handler.addNotedPosition("outside altar", new Position(3312, 3253));
        handler.addNotedPosition("inside altar", new Position(2576, 4848));
        buildTree(BankLocation.DUEL_ARENA);
    }
}
