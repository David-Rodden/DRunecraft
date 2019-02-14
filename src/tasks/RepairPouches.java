package tasks;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import task_structure.TreeTask;

public class RepairPouches extends TreeTask {
    public RepairPouches() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Npc darkMage = Npcs.getNearest("Dark mage");
        if (darkMage != null && !Players.getLocal().isMoving()) {
            darkMage.interact("Repairs");
            Time.sleepUntil(Dialog::isOpen, Random.high(2000, 3000));
        }
        return super.execute();
    }

    @Override
    public String toString() {
        return "Repairing pouches";
    }
}
