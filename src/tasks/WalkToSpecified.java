package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeTask;

public class WalkToSpecified extends TreeTask {
    private final Position destination;

    WalkToSpecified(final Position destination) {
        super(true);
        this.destination = destination;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        if (destination == null) {
            System.out.println("destination is null");
            return super.execute();
        }
        if (!Movement.isRunEnabled() && Movement.getRunEnergy() > 20) {
            Movement.toggleRun(true);
            Time.sleepUntil(Movement::isRunEnabled, 1000);
        }
        Movement.walkTo(destination);
        Time.sleepUntil(() -> !Movement.isDestinationSet() || Movement.getDestinationDistance() < 5, 5000);
        return super.execute();
    }
}
