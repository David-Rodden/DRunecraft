package tasks;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class ShouldHopToPvP extends TreeTask {
    private final TreeScript handler;

    public ShouldHopToPvP(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Position courtyardPosition = handler.getNotedPosition("courtyard");
        return courtyardPosition != null && courtyardPosition.distance() < 10;
    }
}
