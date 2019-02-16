package methods;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import utils.RuneTypes;

public class SimpleBody extends DefaultWalkMethod {
    public SimpleBody(final TreeScript handler) {
        super(handler, RuneTypes.BODY.getId(), RuneTypes.BODY.toString());
        handler.addNotedPosition("bank", new Position(3091, 3491));
        handler.addNotedPosition("outside altar", new Position(3055, 3443));
        handler.addNotedPosition("inside altar", RuneTypes.BODY.getAltarPosition());
        buildTree(BankLocation.EDGEVILLE);
    }
}
