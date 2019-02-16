package methods;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import utils.RuneTypes;

public class SimpleMind extends DefaultWalkMethod {
    SimpleMind(final TreeScript handler) {
        super(handler, RuneTypes.MIND.getId(), RuneTypes.MIND.toString());
        handler.addNotedPosition("bank", new Position(2945, 3372));
        handler.addNotedPosition("outside altar", new Position(2980, 3512));
        handler.addNotedPosition("inside altar", RuneTypes.MIND.getAltarPosition());
        buildTree(BankLocation.FALADOR_WEST);
    }
}
