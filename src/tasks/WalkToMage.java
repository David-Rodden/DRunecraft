package tasks;

import org.rspeer.runetek.api.component.InterfaceAddress;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Players;
import task_structure.TreeScript;

public class WalkToMage extends WalkToSpecified {
    private final int combatLevel;
    private final InterfaceAddress wildernessLevelAddress;

    public WalkToMage(final TreeScript handler) {
        super(handler.getNotedPosition("mage"));
        combatLevel = Players.getLocal().getCombatLevel();
        wildernessLevelAddress = new InterfaceAddress(() -> Interfaces.getFirst(component -> component.getText().contains("Level: ")));
    }

    @Override
    public int execute() {
        // TODO: 2/12/2019 Fix Abyss pking threat
//        final InterfaceComponent wildernessInterface = Interfaces.lookup(wildernessLevelAddress);
//        if (wildernessInterface == null) return super.execute();
//        final int wildernessLevel = Integer.parseInt(wildernessInterface.getText().replaceAll("\\D+", ""));
//        final Player[] threats = Players.getLoaded(player -> Math.abs(combatLevel - player.getCombatLevel()) >= wildernessLevel);
//        if (threats == null || threats.length == 0) return super.execute();
//        final int currentWorld = Worlds.getCurrent();
//        WorldHopper.randomHopInP2p();
//        Time.sleepUntil(() -> Worlds.getCurrent() != currentWorld, 5000);
//        return Random.high(500, 1000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Walking to Mage of Zamorak";
    }
}
