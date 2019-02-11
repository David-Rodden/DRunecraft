package methods;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;
import tasks.HasRunes;
import tasks.OpenBank;
import tasks.WalkToMage;
import utils.AbyssObstacles;

public class Abyss extends CraftMethod {
    public Abyss(final TreeScript handler) {
        super(handler, CraftMethods.ABYSS.getId());
        final int miniquestCompleted = Varps.get(491);  //1073799168?
        handler.addNotedPosition("edgeville", new Position(3088, 3502));
        handler.addNotedPosition("mage", new Position(3107, 3557));
        handler.addNotedPosition("center abyss", new Position(3037, 4830));
        handler.addNotedPosition("outer ffa", new Position(3352, 3164));
        handler.addNotedPosition("inner ffa", new Position(3327, 4751));
        handler.addNotedPosition("clan wars area", new Position(3369, 3170));
        for (final AbyssObstacles obstacle : AbyssObstacles.getMiningLoadout())
            handler.addNotedFlag(obstacle.toString());
        final TreeTask head = new HasRunes("Nature");
        head.setLeft(new WalkToMage(handler));
        head.setRight(new OpenBank());
        setHead(head);
    }
}
