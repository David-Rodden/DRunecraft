package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class TeleportToEdgeville extends TreeTask {
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
        EquipmentSlot.NECK.interact("Edgeville");
        final Position edgevillePosition = handler.getNotedPosition("edgeville");
        Time.sleepUntil(() -> edgevillePosition != null && edgevillePosition.distance() < 40, 4000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Teleporting to Edgeville";
    }
}
