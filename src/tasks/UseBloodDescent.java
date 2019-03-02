package tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class UseBloodDescent extends TreeTask {
    private final TreeScript handler;

    public UseBloodDescent(final TreeScript handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Position bloodDescentPosition = handler.getNotedPosition("blood descent outer");
        final SceneObject shortcut = SceneObjects.getNearest(sceneObject -> sceneObject.getName().equals("Rocks") && sceneObject.containsAction("Climb") && sceneObject.distance(bloodDescentPosition) <= 2);
        if (shortcut != null) {
            shortcut.interact("Climb");
            Time.sleepUntil(() -> !Players.getLocal().isAnimating() && bloodDescentPosition.distance() > 1, Random.high(3800, 4200));
        }
        return super.execute();
    }

    @Override
    public String toString() {
        return "Using blood descent shortcut";
    }
}
