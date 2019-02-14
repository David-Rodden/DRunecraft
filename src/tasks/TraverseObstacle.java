package tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.scene.SceneObjects;
import task_structure.TreeScript;
import task_structure.TreeTask;
import utils.AbyssObstacles;

public class TraverseObstacle extends TreeTask {
    private final static int CLICK_DISTANCE = 2;
    private final TreeScript handler;

    public TraverseObstacle(final TreeScript handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final SceneObject obstacle = SceneObjects.getNearest(traversable -> handler.getNotedSetting(traversable.getName()));
        if (obstacle == null) return super.execute();
        obstacle.interact(AbyssObstacles.valueOf(obstacle.getName().toUpperCase()).getAction());
        Time.sleepUntil(() -> !obstacle.isPositionInteractable(), Random.high(800, obstacle.distance() > CLICK_DISTANCE ? 2000 : 1000));
        return super.execute();
    }

    @Override
    public String toString() {
        return "Traversing Abyss obstacle";
    }
}
