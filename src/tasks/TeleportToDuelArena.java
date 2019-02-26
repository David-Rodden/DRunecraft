package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class TeleportToDuelArena extends TreeTask {
    private final TreeScript handler;

    public TeleportToDuelArena(final TreeScript handler) {
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
        EquipmentSlot.RING.interact("Duel arena");
        final Position duelArenaPosition = handler.getNotedPosition("duel arena");
        Time.sleepUntil(() -> duelArenaPosition != null && duelArenaPosition.distance() < 10, 4000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Teleporting to Duel Arena";
    }
}
