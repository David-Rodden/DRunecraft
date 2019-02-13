package tasks;

import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import task_structure.TreeTask;

public class IsRingEquipped extends TreeTask {
    private final boolean usingMinimumCharge;

    public IsRingEquipped() {
        this(false);
    }

    public IsRingEquipped(final boolean minimumCharge) {
        super(false);
        this.usingMinimumCharge = minimumCharge;
    }

    @Override
    public boolean validate() {
        final String ringName = EquipmentSlot.RING.getItemName();
        if (ringName == null) return false;
        if (!usingMinimumCharge) return !ringName.isEmpty();
        final String ringCharge = ringName.replaceAll("\\D", "");
        return !ringCharge.isEmpty() && Integer.parseInt(ringCharge) >= 2;
    }
}
