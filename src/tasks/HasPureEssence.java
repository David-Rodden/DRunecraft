package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class HasPureEssence extends TreeTask {
    public HasPureEssence() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Inventory.contains("Pure essence");
    }
}
