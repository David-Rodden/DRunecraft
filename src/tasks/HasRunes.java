package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class HasRunes extends TreeTask {
    public HasRunes() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Inventory.contains(" rune");
    }
}
