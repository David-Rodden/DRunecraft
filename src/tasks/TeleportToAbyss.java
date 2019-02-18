package tasks;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.ui.Log;
import task_structure.TreeScript;

import java.util.Arrays;

public class TeleportToAbyss extends WildernessWalkToSpecified {

    private final TreeScript handler;

    public TeleportToAbyss(final TreeScript handler) {
        super(handler);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Player[] threats = getThreats();
        if (threats != null && threats.length > 0) {
            Log.info("We're being hunted by " + Arrays.stream(threats).findAny().get().getName());
            handler.setNotedFlag("hunted", true);
            return 100;
        }
        final Npc zamorakMage = Npcs.getNearest("Mage of Zamorak");
        if (zamorakMage == null) return super.execute();
        zamorakMage.interact("Teleport");
        final Position centerAbyssPosition = handler.getNotedPosition("center abyss");
        Time.sleepUntil(() -> centerAbyssPosition != null && centerAbyssPosition.distance() < 120, 2500);
        return 100;
    }

    @Override
    public String toString() {
        return "Teleporting to Abyss";
    }
}
