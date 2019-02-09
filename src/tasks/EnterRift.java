package tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.SceneObjects;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class EnterRift extends TreeTask {
    private final TreeScript handler;

    public EnterRift(final TreeScript handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final SceneObject rift = SceneObjects.getNearest("Nature rift");
        final Position centerAbyss = handler.getNotedPosition("center abyss");
        if (rift != null && centerAbyss != null) {
            rift.interact("Exit-through");
            Time.sleepUntil(() -> centerAbyss.distance() > 100, Random.high(2000, 3000));
        }
        return super.execute();
    }
}
