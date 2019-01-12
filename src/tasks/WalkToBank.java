package tasks;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeTask;

public class WalkToBank extends TreeTask {
    public WalkToBank() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Position bank = BankLocation.getNearest().getPosition();
        if (bank == null) return super.execute();
        if (!Movement.isRunEnabled() && Movement.getRunEnergy() > 20) {
            Movement.toggleRun(true);
            Time.sleepUntil(Movement::isRunEnabled, 1000);
        }
        Movement.walkTo(bank);
        Time.sleepUntil(() -> !Movement.isDestinationSet() || Movement.getDestinationDistance() < 5, 5000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Walking to bank";
    }
}
