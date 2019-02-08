package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;
import utils.Pouches;

public class HasDamagedPouches extends TreeTask {

    public HasDamagedPouches() {
        super(false);
    }

    @Override
    public boolean validate() {
        for (final Pouches pouch : Pouches.values())
            if (Inventory.contains(pouch.toString()) && !Inventory.contains(pouch.getId())) return false;
        return true;
    }
}
