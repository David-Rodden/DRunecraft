package tasks;

import methods.CraftMethod;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class HasNecessaryItems extends TreeTask {
    public HasNecessaryItems() {
        super(false);
    }

    @Override
    public boolean validate() {
        final String gloryName = EquipmentSlot.NECK.getItemName(), ringName = EquipmentSlot.RING.getItemName();
        if (gloryName == null || ringName == null) return false;
        return Inventory.isFull() && Inventory.contains(CraftMethod.PURE_ESSENCE_ID) && gloryName.contains("Amulet of glory(") && ringName.contains("Ring of dueling");
    }
}
