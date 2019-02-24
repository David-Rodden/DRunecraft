package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import task_structure.TreeScript;

public class WalkToEssence extends WalkToSpecified {
    public WalkToEssence(final TreeScript handler) {
        super(handler.getNotedPosition("center essence"), true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        if (Movement.isDestinationSet() && Inventory.contains("Dark essence block"))
            FragmentizeBlocks.fragmentize(true);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Walking to dense essence mine";
    }
}
