package tasks;

import task_structure.TreeTask;
import utils.AbyssEquipment;

public class IsWearingAbyssEquipment extends TreeTask {
    private final AbyssEquipment equipment;

    public IsWearingAbyssEquipment(final AbyssEquipment equipment) {
        super(false);
        this.equipment = equipment;
    }

    @Override
    public boolean validate() {
        return equipment.isWearingEquipment();
    }
}
