package tasks;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.scene.Npcs;
import task_structure.TreeTask;

public class IsNearMage extends TreeTask {
    public IsNearMage() {
        super(false);
    }

    @Override
    public boolean validate() {
        final Npc zamorakMage = Npcs.getNearest("Mage of Zamorak");
        return zamorakMage != null && zamorakMage.isPositionInteractable();
    }
}
