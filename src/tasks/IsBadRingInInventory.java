package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class IsBadRingInInventory extends TreeTask {
    public IsBadRingInInventory() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Inventory.contains("Ring of dueling(1)");
    }
}
