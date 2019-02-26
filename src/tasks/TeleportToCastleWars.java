package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class TeleportToCastleWars extends TreeTask {
    private final TreeScript handler;

    public TeleportToCastleWars(final TreeScript handler) {
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
        EquipmentSlot.RING.interact("Castle Wars");
        final Position castlewarsPosition = handler.getNotedPosition("castle wars");
        Time.sleepUntil(() -> castlewarsPosition != null && castlewarsPosition.distance() < 20, 4000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Teleporting to Castle Wars";
    }
}
