package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class HasDarkEssence extends TreeTask {
    public HasDarkEssence() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Inventory.contains("Dark essence block");
    }
}
