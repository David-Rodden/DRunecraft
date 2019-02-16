package methods;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import utils.RuneTypes;

public class SimpleAir extends DefaultWalkMethod {
    public SimpleAir(final TreeScript handler) {
        super(handler, RuneTypes.AIR.getId(), RuneTypes.AIR.toString());
        handler.addNotedPosition("bank", new Position(3012, 3355));
        handler.addNotedPosition("outside altar", new Position(2986, 3294));
        handler.addNotedPosition("inside altar", RuneTypes.AIR.getAltarPosition());
        buildTree(BankLocation.FALADOR_EAST);
    }
}
