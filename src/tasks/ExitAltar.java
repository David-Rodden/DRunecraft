package tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.SceneObjects;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class ExitAltar extends TreeTask {
    private final TreeScript handler;

    public ExitAltar(final TreeScript handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final SceneObject portal = SceneObjects.getNearest("Portal");
        if (portal == null) return super.execute();
        portal.interact("Use");
        final Position ruinsPosition = handler.getNotedPosition("outside altar");
        Time.sleepUntil(() -> ruinsPosition != null && ruinsPosition.distance() < 20, 5000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Exiting altar";
    }
}
