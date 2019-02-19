package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
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
