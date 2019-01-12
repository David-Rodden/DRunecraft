package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class EquipRing extends TreeTask {
    public EquipRing() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Item ring = Inventory.getFirst(item -> item.getName().contains("Dueling ring"));
        if (ring == null) return super.execute();
        ring.interact("Wear");
        Time.sleepUntil(() -> !Inventory.contains(item -> item.getName().contains("Dueling ring")), 3000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Equipping dueling ring";
    }
}
