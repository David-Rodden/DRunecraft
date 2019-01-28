package tasks;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class IsNearCastlewars extends TreeTask {
    private final TreeScript handler;

    public IsNearCastlewars(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Position castlewarsPosition = handler.getNotedPosition("castle wars");
        return castlewarsPosition != null && castlewarsPosition.distance() < 20;
    }
}
