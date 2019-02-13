package tasks;

import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import task_structure.TreeTask;

public class IsGloryEquipped extends TreeTask {
    public IsGloryEquipped() {
        super(false);
    }

    @Override
    public boolean validate() {
        final String gloryName = EquipmentSlot.NECK.getItemName();
        if (gloryName == null) return false;
        return gloryName.contains("Amulet of glory(");
    }
}
