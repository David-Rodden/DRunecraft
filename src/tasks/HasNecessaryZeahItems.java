package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class HasNecessaryZeahItems extends TreeTask {
    public HasNecessaryZeahItems() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Inventory.contains("Dark essence fragments") && Inventory.contains("Dark essence block") && Inventory.isFull();
    }
}
