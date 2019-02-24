package tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import task_structure.TreeTask;

public class BindBloodAltar extends TreeTask {
    public BindBloodAltar() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final SceneObject altar = SceneObjects.getNearest("Blood Altar");
        if (altar == null) return super.execute();
        altar.interact("Bind");
        Time.sleepUntil(() -> !Inventory.contains("Dark essence fragments") && !Players.getLocal().isAnimating(), 5000);
        Time.sleep(750, 800);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Crafting runes";
    }
}
