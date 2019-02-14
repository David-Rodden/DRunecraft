package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class IsBadGloryInInventory extends TreeTask {
    public IsBadGloryInInventory() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Inventory.contains("Amulet of glory");
    }
}
