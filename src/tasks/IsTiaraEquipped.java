package tasks;

import org.rspeer.runetek.api.component.tab.Equipment;
import task_structure.TreeTask;

public class IsTiaraEquipped extends TreeTask {
    private final String runeType;

    public IsTiaraEquipped(final String runeType) {
        super(false);
        this.runeType = runeType;
    }

    @Override
    public boolean validate() {
        return Equipment.contains(runeType + " tiara");
    }
}
