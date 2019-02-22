package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class HasDarkEssenceFragments extends TreeTask {
    public HasDarkEssenceFragments() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Inventory.contains("Dark essence fragments");
    }
}
