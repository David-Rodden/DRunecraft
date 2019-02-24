package tasks;

import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.ui.Log;
import task_structure.TreeScript;
import task_structure.TreeTask;
import utils.Pathing;

public class IsNearBloodAltar extends TreeTask {
    private final TreeScript handler;

    public IsNearBloodAltar(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Position bloodAltarPosition = handler.getNotedPosition("blood altar");
        return bloodAltarPosition != null && Pathing.getTrueDistance(bloodAltarPosition) < 100;
    }
}
