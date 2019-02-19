package tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.SceneObjects;
import task_structure.TreeTask;

public class VenerateAltar extends TreeTask {
    public VenerateAltar() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final SceneObject darkAltar = SceneObjects.getNearest("Dark altar");
        if (darkAltar == null) return super.execute();
        darkAltar.interact("Venerate");
        Time.sleepUntil(() -> Inventory.contains("Dark essence block"), 1500);
        return super.execute();
    }
}
