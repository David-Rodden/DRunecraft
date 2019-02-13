package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class IsEnoughEssenceInInventory extends TreeTask {
    public IsEnoughEssenceInInventory() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Inventory.isFull();
    }
}
