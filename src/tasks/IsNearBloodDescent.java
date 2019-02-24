package tasks;

import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.ui.Log;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class IsNearBloodDescent extends TreeTask {
    private final TreeScript handler;

    public IsNearBloodDescent(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Position bloodDescentPosition = handler.getNotedPosition("blood descent outer");
        return bloodDescentPosition != null && bloodDescentPosition.distance() < 10;
    }
}
