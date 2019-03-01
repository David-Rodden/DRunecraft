package methods;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import utils.RuneTypes;

public class ArceuusSoul extends CraftMethod {
    ArceuusSoul(final TreeScript handler) {
        super(RuneTypes.SOUL.getId());

        handler.addNotedPosition("soul descent outer", new Position(1776, 3884));
    }
}
