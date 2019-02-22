package tasks;

import methods.CraftMethod;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class HasNecessaryAbyssItems extends TreeTask {
    private final TreeScript handler;

    public HasNecessaryAbyssItems(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final String gloryName = EquipmentSlot.NECK.getItemName(), ringName = EquipmentSlot.RING.getItemName();
        if (gloryName == null || ringName == null) return false;
        return Inventory.isFull() && Inventory.contains(CraftMethod.PURE_ESSENCE_ID) && gloryName.contains("Amulet of glory(") && (ringName.contains("Ring of dueling") || !handler.getNotedSetting("clan wars"));
    }
}
