package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import task_structure.TreeTask;

public class WalkToSpecified extends TreeTask {
    private static final int MAXIMUM_DESTINATION_DISTANCE = 5;
    private final Position destination;
    private final boolean allowForRunAction;
    private int runThreshold;

    WalkToSpecified(final Position destination) {
        this(destination, false);
    }

    WalkToSpecified(final Position destination, final boolean allowForRunAction) {
        super(true);
        this.destination = destination;
        this.allowForRunAction = allowForRunAction;
        runThreshold = Random.high(15, 30);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        if (destination == null) return super.execute();
        if (!Movement.isRunEnabled() && Movement.getRunEnergy() > runThreshold) {
            Movement.toggleRun(true);
            Time.sleepUntil(Movement::isRunEnabled, Random.high(950, 1400));
            runThreshold = Random.high(15, 30);
        }
        Movement.walkTo(destination);
        Time.sleepUntil(() -> !Players.getLocal().isAnimating() && (!allowForRunAction && !Movement.isDestinationSet()) || Movement.getDestinationDistance() < MAXIMUM_DESTINATION_DISTANCE * (allowForRunAction ? 2 : 1), Random.high(5000, 6000));
        return super.execute();
    }
}
