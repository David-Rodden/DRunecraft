package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class IsRingInInventory extends TreeTask {
    public IsRingInInventory() {
        super(false);
    }

    @Override
    public boolean validate() {
        for (int charge = 8; charge >= 2; charge--)
            if (Inventory.contains("Ring of dueling(" + charge + ')')) return true;
        return false;
    }
}
