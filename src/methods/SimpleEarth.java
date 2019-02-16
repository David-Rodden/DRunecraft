package methods;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import utils.RuneTypes;

public class SimpleEarth extends DefaultWalkMethod {
    public SimpleEarth(final TreeScript handler) {
        super(handler, RuneTypes.EARTH.getId(), RuneTypes.EARTH.toString());
        handler.addNotedPosition("bank", new Position(3254, 3423));
        handler.addNotedPosition("outside altar", new Position(3305, 3472));
        handler.addNotedPosition("inside altar", RuneTypes.EARTH.getAltarPosition());
        buildTree(BankLocation.VARROCK_EAST);
    }
}
