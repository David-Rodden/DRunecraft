package tasks;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class IsNextToBloodAltar extends TreeTask {
    private final TreeScript handler;

    public IsNextToBloodAltar(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Position bloodAltarPosition = handler.getNotedPosition("blood altar");
        return bloodAltarPosition != null && bloodAltarPosition.distance() < 10;
    }
}
