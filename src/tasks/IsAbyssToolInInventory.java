package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class IsAbyssToolInInventory extends TreeTask {
    private final String tool;

    public IsAbyssToolInInventory(final TreeScript handler) {
        super(false);
        tool = handler.getNotedSetting("pickaxe") ? "pickaxe" : "axe";

    }

    @Override
    public boolean validate() {
        return Inventory.contains(item -> item.getName().matches("\\D+\\s" + tool));
    }
}
