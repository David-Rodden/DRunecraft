package tasks;

import org.rspeer.runetek.api.Worlds;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.WorldHopper;
import org.rspeer.runetek.providers.RSWorld;
import task_structure.TreeTask;

public class HopToPvP extends TreeTask {
    public HopToPvP() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final int currentWorld = Worlds.getCurrent();
        WorldHopper.hopNext(RSWorld::isPVP);
        Time.sleepUntil(() -> currentWorld != Worlds.getCurrent(), 8000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Hopping to PvP world";
    }
}
