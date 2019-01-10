package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class HaveBadRings extends TreeTask {
    public HaveBadRings() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Inventory.contains("Ring of dueling(1)");
    }
}
