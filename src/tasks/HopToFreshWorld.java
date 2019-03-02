package tasks;

import org.rspeer.runetek.api.Worlds;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.WorldHopper;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class HopToFreshWorld extends TreeTask {
    private final TreeScript handler;

    public HopToFreshWorld(final TreeScript handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final int currentWorld = Worlds.getCurrent();
        if (!WorldHopper.isOpen()) WorldHopper.open();
        Time.sleepUntil(WorldHopper::isOpen, Random.high(800, 1300));
        WorldHopper.randomHopInP2p();
        Time.sleepUntil(() -> Worlds.getCurrent() != currentWorld, Random.high(4700, 5400));
        handler.setNotedFlag("hunted", false);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Hopping to another world to lose pker threat";
    }
}
