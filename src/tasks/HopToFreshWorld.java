package tasks;

import org.rspeer.runetek.api.Worlds;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.WorldHopper;
import org.rspeer.ui.Log;
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
        Time.sleepUntil(WorldHopper::isOpen, 1000);
        WorldHopper.randomHopInP2p();
        Time.sleepUntil(() -> Worlds.getCurrent() != currentWorld, 5000);
        handler.setNotedFlag("hunted", false);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Hopping to another world to lose pker threat";
    }
}
