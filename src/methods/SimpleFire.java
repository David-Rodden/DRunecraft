package methods;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import utils.RuneTypes;

public class SimpleFire extends DefaultWalkMethod {

    public SimpleFire(final TreeScript handler) {
        super(handler, RuneTypes.FIRE.getId(), RuneTypes.FIRE.toString());
        handler.addNotedPosition("bank", new Position(3382, 3267));
        handler.addNotedPosition("outside altar", new Position(3312, 3253));
        handler.addNotedPosition("inside altar", RuneTypes.FIRE.getAltarPosition());
        buildTree(BankLocation.DUEL_ARENA);
    }
}
