package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class IsMindTabInInventory extends TreeTask {
    public IsMindTabInInventory() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Inventory.contains("Mind altar teleport");
    }
}
