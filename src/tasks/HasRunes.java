package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class HasRunes extends TreeTask {
    private final String type;

    public HasRunes(final String type) {
        super(false);
        this.type = type;
    }

    @Override
    public boolean validate() {
        return Inventory.contains(type + " rune");
    }
}
