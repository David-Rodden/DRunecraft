package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import task_structure.TreeTask;

public class FragmentizeBlocks extends TreeTask {
    public FragmentizeBlocks() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        fragmentize(false);
        return super.execute();
    }

    static void fragmentize(final boolean mustBeRunning) {
        final Item chisel = Inventory.getFirst("Chisel");
        if (chisel == null) return;
        for (final Item block : Inventory.getItems(item -> item.getName().equals("Dark essence block"))) {
            if (mustBeRunning && (!Movement.isDestinationSet() || Movement.getDestinationDistance() < 5)) return;
            if (!Inventory.isItemSelected()) {
                chisel.interact("Use");
                Time.sleepUntil(Inventory::isItemSelected, 1000);
            }
            if (block == null) continue;
            block.interact("Use");
        }
    }

    @Override
    public String toString() {
        return "Turning dark blocks into fragments";
    }
}
