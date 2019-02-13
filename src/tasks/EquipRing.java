package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class EquipRing extends TreeTask {
    private final boolean isUsingMinimumCharge;

    public EquipRing() {
        this(false);
    }

    public EquipRing(final boolean isUsingMinimumCharge) {
        super(true);
        this.isUsingMinimumCharge = isUsingMinimumCharge;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Item ring = Inventory.getFirst(item -> item.getName().contains("Ring of dueling"));
        if (ring == null) return super.execute();
        ring.interact("Wear");
        Time.sleepUntil(() -> !Inventory.contains(item -> item.getName().matches(isUsingMinimumCharge ? "Ring of dueling\\([2-8]\\)" : "Ring of dueling.*")), 3000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Equipping dueling ring";
    }
}
