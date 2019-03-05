package tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import task_structure.TreeTask;

public class CraftRunes extends TreeTask {
    private final boolean shouldTeleportOut;

    public CraftRunes() {
        this(false);
    }

    public CraftRunes(final boolean shouldTeleportOut) {
        super(true);
        this.shouldTeleportOut = shouldTeleportOut;
    }

    @Override
    public boolean validate() {
        final SceneObject altar = SceneObjects.getNearest("Altar");
        return altar != null && Inventory.contains("Pure essence");
    }

    @Override
    public int execute() {
        final SceneObject altar = SceneObjects.getNearest("Altar");
        if (altar == null) return super.execute();
        altar.interact("Craft-rune");
        Time.sleepUntil(() -> !Inventory.contains("Pure essence") && !Players.getLocal().isAnimating(), Random.high(4900, 5100));
        if (shouldTeleportOut) Time.sleep(680, 800);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Crafting runes";
    }

}
