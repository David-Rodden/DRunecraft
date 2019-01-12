package tasks;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class IsNearJiminua extends TreeTask {
    private final TreeScript handler;

    public IsNearJiminua(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Position generalStorePosition = handler.getNotedPosition("general store");
        return generalStorePosition != null && generalStorePosition.distance() < 10;
    }
}
