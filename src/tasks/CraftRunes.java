package tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class CraftRunes extends TreeTask {
    private final TreeScript handler;

    public CraftRunes(final TreeScript handler) {
        super(true);
        this.handler = handler;
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
        Time.sleepUntil(() -> !Inventory.contains("Pure essence") && !Players.getLocal().isAnimating(), 5000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Crafting runes";
    }

}
