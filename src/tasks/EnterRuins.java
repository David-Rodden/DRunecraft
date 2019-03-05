package tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.SceneObjects;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class EnterRuins extends TreeTask {
    private final TreeScript handler;

    public EnterRuins(final TreeScript handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final SceneObject ruins = SceneObjects.getNearest("Mysterious ruins");
        if (ruins == null) return super.execute();
        ruins.interact("Enter");
        final Position altarPosition = handler.getNotedPosition("inside altar");
        Time.sleepUntil(() -> altarPosition != null && altarPosition.distance() < 20, Random.high(2500, 2700));
        return super.execute();
    }

    @Override
    public String toString() {
        return "Entering ruins";
    }
}
