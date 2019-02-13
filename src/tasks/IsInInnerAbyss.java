package tasks;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.scene.Npcs;
import task_structure.TreeTask;

public class IsInInnerAbyss extends TreeTask {

    public IsInInnerAbyss() {
        super(false);
    }

    @Override
    public boolean validate() {
        final Npc darkMage = Npcs.getNearest("Dark mage");
        return darkMage != null && darkMage.isPositionInteractable();
    }
}
