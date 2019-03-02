package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class TeleportToClanWars extends TreeTask {
    private final TreeScript handler;

    public TeleportToClanWars(final TreeScript handler) {
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
        EquipmentSlot.RING.interact("Clan wars");
        final Position clanWarsPosition = handler.getNotedPosition("clan wars");
        Time.sleepUntil(() -> clanWarsPosition != null && clanWarsPosition.distance() < 40, Random.high(3700, 4200));
        return super.execute();
    }

    @Override
    public String toString() {
        return "Teleporting to Clan Wars";
    }
}
