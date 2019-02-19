package tasks;

import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.SceneObjects;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class ShouldVenerateAltar extends TreeTask {
    private final TreeScript handler;

    public ShouldVenerateAltar(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Position darkAltarPosition = handler.getNotedPosition("dark altar");
        return SceneObjects.getNearest("Dark altar") != null && darkAltarPosition != null && darkAltarPosition.distance() < 10;
    }
}
