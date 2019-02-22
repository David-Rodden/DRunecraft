package tasks;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class IsNearEssenceMine extends TreeTask {
    private final TreeScript handler;

    public IsNearEssenceMine(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Position essencePosition = handler.getNotedPosition("center essence");
        return essencePosition != null && essencePosition.distance() < 10;
    }
}
