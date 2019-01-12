package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class WalkToRuins extends TreeTask {
    private final TreeScript handler;

    public WalkToRuins(final TreeScript handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Position outsideAltar = handler.getNotedPosition("outside altar");
        if (outsideAltar == null) return super.execute();
        if (!Movement.isRunEnabled() && Movement.getRunEnergy() > 20) {
            Movement.toggleRun(true);
            Time.sleepUntil(Movement::isRunEnabled, 1000);
        }
        Movement.walkTo(outsideAltar);
        Time.sleepUntil(() -> !Movement.isDestinationSet() || Movement.getDestinationDistance() < 5, 5000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Walking to ruins";
    }
}
