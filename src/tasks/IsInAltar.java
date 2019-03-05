package tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.ui.Log;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class IsInAltar extends TreeTask {
    private final TreeScript handler;

    public IsInAltar(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final SceneObject portal = SceneObjects.getNearest("Portal");
        final Position altarPosition = handler.getNotedPosition("inside altar");
        return (portal != null && portal.containsAction("Use")) || (altarPosition != null && altarPosition.distance() < 20);
    }
}
