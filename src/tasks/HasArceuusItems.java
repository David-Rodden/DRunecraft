package tasks;

import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class HasArceuusItems extends TreeTask {
    public HasArceuusItems() {
        super(false);
    }

    @Override
    public boolean validate() {
        final String ringName = EquipmentSlot.RING.getItemName();
        if(ringName == null)    return false;
        return Inventory.isFull() && Inventory.contains("Mind altar teleport") && ringName.contains("Ring of dueling");
    }
}
