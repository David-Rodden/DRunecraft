package tasks;

import methods.CraftMethod;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class IsFullOfEssence extends TreeTask {
    public IsFullOfEssence() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Inventory.isFull() && Inventory.contains(CraftMethod.PURE_ESSENCE_ID);
    }
}
