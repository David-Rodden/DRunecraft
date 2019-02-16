package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class TeleportToMindAltar extends TreeTask {
    private final TreeScript handler;

    public TeleportToMindAltar(final TreeScript handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Item teleportTab = Inventory.getFirst("Mind altar teleport");
        final Position outsideAltar = handler.getNotedPosition("outside altar");
        if (teleportTab != null && outsideAltar != null) {
            teleportTab.interact("Break");
            Time.sleepUntil(() -> outsideAltar.distance() < 20, Random.high(3500, 4000));
        }
        return super.execute();
    }

    @Override
    public String toString() {
        return "Teleporting to Mind altar";
    }
}
