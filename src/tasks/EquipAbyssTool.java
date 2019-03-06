package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class EquipAbyssTool extends TreeTask {
    private final String tool;

    public EquipAbyssTool(final TreeScript handler) {
        super(true);
        tool = handler.getNotedSetting("pickaxe") ? "pickaxe" : "axe";
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Item toEquip = Inventory.getFirst(item -> item.getName().matches("\\D+\\s" + tool));
        if (toEquip == null) return super.execute();
        final String toEquipName = toEquip.getName();
        toEquip.interact("Wield");
        Time.sleepUntil(() -> Equipment.contains(toEquipName), Random.high(900, 1200));
        return super.execute();
    }

    @Override
    public String toString() {
        return "Equipping " + tool;
    }
}
