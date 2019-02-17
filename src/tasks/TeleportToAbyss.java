package tasks;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.Definitions;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.ui.Log;
import task_structure.TreeScript;
import task_structure.TreeTask;

import java.util.Arrays;

public class TeleportToAbyss extends TreeTask {
    private final static int WILDERNESS_ENTRANCE_Y = 3524, MAXIMUM_WILDERNESS = 6, EQUIPMENT_OFFSET = 512;
    private final TreeScript handler;
    private final String characterName;
    private final int characterLevel;

    public TeleportToAbyss(final TreeScript handler) {
        super(true);
        this.handler = handler;
        final Player player = Players.getLocal();
        characterName = player.getName();
        characterLevel = player.getCombatLevel();
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Player[] threats = Players.getLoaded(player -> {
            if (player.getName().equals(characterName)) return false;
            for (final int equipmentId : player.getAppearance().getEquipmentIds()) {
                if (equipmentId <= EQUIPMENT_OFFSET) continue;
                if (Definitions.getItem(equipmentId - EQUIPMENT_OFFSET).getName().matches(".*\\s(pickaxe|axe)"))
                    return false;
            }
            return player.getPosition().getY() > WILDERNESS_ENTRANCE_Y && Math.abs(characterLevel - player.getCombatLevel()) <= MAXIMUM_WILDERNESS;
        });
        if (threats != null && threats.length > 0) {
            Log.info("We're being hunted by " + Arrays.stream(threats).findAny().get().getName());
            handler.setNotedFlag("hunted", true);
            return 100;
        }
        final Npc zamorakMage = Npcs.getNearest("Mage of Zamorak");
        if (zamorakMage == null) return super.execute();
        zamorakMage.interact("Teleport");
        final Position centerAbyssPosition = handler.getNotedPosition("center abyss");
        Time.sleepUntil(() -> centerAbyssPosition != null && centerAbyssPosition.distance() < 100, 3000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Teleporting to Abyss";
    }
}
