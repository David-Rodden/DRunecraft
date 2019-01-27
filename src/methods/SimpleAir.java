package methods;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;

public class SimpleAir extends DefaultWalkMethod {
    public SimpleAir(final TreeScript handler) {
        super(handler, 556, "Air");
        handler.addNotedPosition("bank", new Position(3012, 3355));
        handler.addNotedPosition("outside altar", new Position(2986, 3294));
        handler.addNotedPosition("inside altar", new Position(2841, 4830));
        buildTree(BankLocation.FALADOR_EAST);
    }
}
