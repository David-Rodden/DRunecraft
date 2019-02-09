package tasks;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class TeleportToAbyss extends TreeTask {
    private final TreeScript handler;

    public TeleportToAbyss(final TreeScript handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Npc zamorakMage = Npcs.getNearest("Mage of Zamorak");
        if (zamorakMage == null) return super.execute();
        zamorakMage.interact("Teleport");
        final Position centerAbyssPosition = handler.getNotedPosition("center abyss");
        Time.sleepUntil(() -> centerAbyssPosition != null && centerAbyssPosition.distance() < 50, 6000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Teleporting to Abyss";
    }
}
