package tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.SceneObjects;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class UseClanWarsPortal extends TreeTask {
    private final TreeScript handler;

    public UseClanWarsPortal(final TreeScript handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final SceneObject portal = SceneObjects.getNearest("Free-for-all portal");
        final Position freeForAllPosition = handler.getNotedPosition("outer ffa");
        if (portal != null && freeForAllPosition != null) {
            portal.interact("Enter");
            Time.sleepUntil(() -> freeForAllPosition.distance() > 10, 3000);
            Time.sleep(1000, 1200);
        }
        return super.execute();
    }

    @Override
    public String toString() {
        return "Replenishing through Clan Wars";
    }
}
