package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class HasEnoughEssence extends TreeTask {
    public HasEnoughEssence() {
        super(false);
    }

    @Override
    public boolean validate() {
        final int essence = 7936;
        return Inventory.isFull() && Inventory.contains(essence);
    }
}
