package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class ShouldChiselDarkEssence extends TreeTask {
    public ShouldChiselDarkEssence() {
        super(false);
    }

    @Override
    public boolean validate() {
        return !Inventory.contains("Dark essence fragments") && Inventory.contains("Dark essence block") ;
    }
}
