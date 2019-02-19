package tasks;

import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.Definitions;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.providers.RSPlayerAppearance;
import org.rspeer.ui.Log;
import task_structure.TreeScript;

import java.util.Arrays;

public class WildernessWalkToSpecified extends WalkToSpecified {
    private final static int WILDERNESS_ENTRANCE_Y = 3524, WILDERNESS_LIMIT_Y = 3567, MAXIMUM_WILDERNESS = 6, EQUIPMENT_OFFSET = 512;
    private final TreeScript handler;
    private final String characterName;
    private final int characterLevel;

    WildernessWalkToSpecified(final TreeScript handler) {
        super(handler.getNotedPosition("mage"));
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
        final Player[] threats = getThreats();
        if (threats == null || threats.length == 0) return super.execute();
        Log.info("We're being hunted by " + Arrays.stream(threats).findAny().get().getName());
        handler.setNotedFlag("hunted", true);
        return 100;
    }

    Player[] getThreats() {
        return Players.getLoaded(player -> {
            final String name = player.getName();
            if (name == null || name.equals(characterName)) return false;
            final RSPlayerAppearance appearance = player.getAppearance();
            if (appearance == null) return false;
            for (final int equipmentId : player.getAppearance().getEquipmentIds()) {
                if (equipmentId <= EQUIPMENT_OFFSET) continue;
                if (Definitions.getItem(equipmentId - EQUIPMENT_OFFSET).getName().matches(".*\\s(pickaxe|axe)"))
                    return false;
            }
            final int playerDepth = player.getPosition().getY();
            return playerDepth > WILDERNESS_ENTRANCE_Y && playerDepth <= WILDERNESS_LIMIT_Y && Math.abs(characterLevel - player.getCombatLevel()) <= MAXIMUM_WILDERNESS;
        });
    }
}