package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class IsFullOfDenseEssence extends TreeTask {
    public IsFullOfDenseEssence() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Inventory.contains("Dense essence block") && Inventory.isFull();
    }
}
