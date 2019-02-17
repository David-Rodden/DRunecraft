package tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.scene.Players;
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
        final SceneObject obstacle = SceneObjects.getNearest(traversable -> {
            final String name = traversable.getName();
            return handler.getNotedSetting(name) && traversable.containsAction(AbyssObstacles.valueOf(name.toUpperCase()).getAction());
        });
        if (obstacle == null)
            return super.execute();
        obstacle.interact(AbyssObstacles.valueOf(obstacle.getName().toUpperCase()).getAction());
        Time.sleepUntil(() -> !obstacle.isPositionInteractable() || (!Players.getLocal().isMoving() && obstacle.distance() > CLICK_DISTANCE), Random.high(400, 700));
        return super.execute();
    }

    @Override
    public String toString() {
        return "Traversing Abyss obstacle";
    }
}
