package tasks;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class IsInFFA extends TreeTask {
    private final TreeScript handler;

    public IsInFFA(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Position ffaPosition = handler.getNotedPosition("inner ffa");
        return ffaPosition != null && ffaPosition.distance() < 30;
    }
}
