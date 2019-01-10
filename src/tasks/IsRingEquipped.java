package tasks;

import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import task_structure.TreeTask;

public class IsRingEquipped extends TreeTask {
    public IsRingEquipped() {
        super(false);
    }

    @Override
    public boolean validate() {
        final String ringName = EquipmentSlot.RING.getItemName();
        if (ringName == null) return false;
        final String ringCharge = ringName.replaceAll("\\D", "");
        return !ringCharge.isEmpty() && Integer.parseInt(ringName) >= 2;
    }
}
