package methods;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import utils.RuneTypes;

public class SimpleWater extends DefaultWalkMethod {
    public SimpleWater(final TreeScript handler) {
        super(handler, RuneTypes.WATER.getId(), "Water");
        handler.addNotedPosition("bank", new Position(3207, 3218, 2));
        handler.addNotedPosition("outside altar", new Position(3183, 3165));
        handler.addNotedPosition("inside altar", RuneTypes.WATER.getAltarPosition());
        buildTree(BankLocation.LUMBRIDGE_CASTLE);
    }
}
