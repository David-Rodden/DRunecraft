package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class HasTiaraInInventory extends TreeTask {
    private final String runeType;

    public HasTiaraInInventory(final String runeType) {
        super(false);
        this.runeType = runeType;
    }

    @Override
    public boolean validate() {
        return Inventory.contains(runeType + " tiara");
    }
}
