package tasks;

import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class HasPureEssenceAndTiara extends TreeTask {
    private final String runeType;

    public HasPureEssenceAndTiara(final String runeType) {
        super(false);
        this.runeType = runeType;
    }

    @Override
    public boolean validate() {
        return Inventory.contains("Pure essence") && Equipment.contains(runeType + " tiara");
    }
}
