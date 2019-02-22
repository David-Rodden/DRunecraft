package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
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
        final Item chisel = Inventory.getFirst("Chisel");
        if (chisel == null) return super.execute();
        for (final Item block : Inventory.getItems(item -> item.getName().equals("Dark essence block"))) {
            if (!Inventory.isItemSelected()) {
                chisel.interact("Use");
                Time.sleepUntil(Inventory::isItemSelected, 1000);
            }
            if (block == null) continue;
            block.interact("Use");
        }
        return super.execute();
    }

    @Override
    public String toString() {
        return "Turning dark blocks into fragments";
    }
}
