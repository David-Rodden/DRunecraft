package tasks;

import org.rspeer.runetek.api.component.tab.Equipment;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class IsAbyssToolEquipped extends TreeTask {

    private final String tool;

    public IsAbyssToolEquipped(final TreeScript handler) {
        super(false);
        tool = handler.getNotedSetting("pickaxe") ? "pickaxe" : handler.getNotedSetting("axe") ? "axe" : "";
    }

    @Override
    public boolean validate() {
        return tool.isEmpty() || Equipment.contains(item -> item.getName().matches("\\D+\\s" + tool));
    }
}
