package methods;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;
import tasks.HasRunes;
import tasks.OpenBank;
import tasks.WalkToMage;

public class Abyss extends CraftMethod {
    public Abyss(final TreeScript handler) {
        super(handler, CraftMethods.ABYSS.getId());
        handler.addNotedPosition("mage", new Position(3107, 3557));
        final TreeTask head = new HasRunes("Nature");
        head.setLeft(new WalkToMage(handler));
        head.setRight(new OpenBank());
        setHead(head);
    }
}
