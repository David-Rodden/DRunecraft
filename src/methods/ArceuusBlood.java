package methods;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;

public class ArceuusBlood extends CraftMethod {
    ArceuusBlood(final TreeScript handler, final int id) {
        super(handler, id);
        handler.addNotedPosition("dark altar", new Position(1718, 3882));
        handler.addNotedPosition("high agility outer", new Position(1761, 3874));
        handler.addNotedPosition("high agility inner", new Position(1761, 3871));
        handler.addNotedPosition("low agility outer", new Position(1774, 3849));
        handler.addNotedPosition("low agility inner", new Position(1769, 3849));
        handler.addNotedPosition("top agility outer", new Position(1742, 3854));
        handler.addNotedPosition("center essence", new Position(1765, 3852));
        handler.addNotedPosition("blood altar", new Position(1719, 3828));

    }
}
