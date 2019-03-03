package tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.scene.SceneObjects;
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
        return portal != null && portal.containsAction("Use");
    }
}
