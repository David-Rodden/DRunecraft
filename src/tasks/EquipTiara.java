package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class EquipTiara extends TreeTask {
    private final String runeType;

    public EquipTiara(final String runeType) {
        super(true);
        this.runeType = runeType;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final String tiaraName = runeType + " tiara";
        final Item tiara = Inventory.getFirst(tiaraName);
        if (tiara != null) {
            tiara.interact("Wear");
            Time.sleepUntil(() -> Equipment.contains(tiaraName), Random.high(1900, 2100));
        }
        return super.execute();
    }
}
