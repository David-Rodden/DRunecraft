package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class TeleportToEdgeville extends TreeTask {
    private static final int ESCAPE_DELAY = 10000;
    private final TreeScript handler;

    public TeleportToEdgeville(final TreeScript handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        Equipment.refreshComponentData();
        final String gloryName = EquipmentSlot.NECK.getItemName();
        if (gloryName == null || gloryName.isEmpty()) {
            final Item inventoryGlory = Inventory.getFirst(item -> item.getName().contains("Amulet of glory("));
            if (inventoryGlory == null) return -1;
            inventoryGlory.interact("Wear");
            Time.sleepUntil(() -> EquipmentSlot.NECK.getItemName().contains("Amulet of glory("), 2000);
        }
        EquipmentSlot.NECK.interact("Edgeville");
        final Position edgevillePosition = handler.getNotedPosition("edgeville");
        Time.sleepUntil(() -> edgevillePosition != null && edgevillePosition.distance() < 40, Random.high(2500, 2700));
        if (handler.getNotedFlag("hunted") && edgevillePosition.distance() < 40) Time.sleep(ESCAPE_DELAY);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Teleporting to Edgeville";
    }
}
