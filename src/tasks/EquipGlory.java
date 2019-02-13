package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

import java.util.regex.Pattern;

public class EquipGlory extends TreeTask {
    private final Pattern gloryPattern;

    public EquipGlory() {
        super(true);
        gloryPattern = Pattern.compile("Amulet of glory\\(\\d\\)");
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Item glory = Inventory.getFirst(gloryPattern);
        if (glory != null) {
            glory.interact("Wear");
            Time.sleepUntil(() -> Inventory.getFirst(gloryPattern) == null, 2000);
        }
        return super.execute();
    }
}
