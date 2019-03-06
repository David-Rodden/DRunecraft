package tasks;

import task_structure.TreeTask;
import utils.AbyssEquipment;

public class IsAbyssEquipmentInInventory extends TreeTask {
    private final AbyssEquipment equipment;

    public IsAbyssEquipmentInInventory(final AbyssEquipment equipment) {
        super(false);
        this.equipment = equipment;
    }

    @Override
    public boolean validate() {
        return equipment.isEquipmentInInventory();
    }
}
