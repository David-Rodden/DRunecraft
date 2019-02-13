package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

import java.util.regex.Pattern;

public class IsGloryInInventory extends TreeTask {
    private final Pattern gloryPattern;

    public IsGloryInInventory() {
        super(false);
        gloryPattern = Pattern.compile("Amulet of glory\\(\\d\\)");
    }

    @Override
    public boolean validate() {
        return Inventory.contains(gloryPattern);
    }
}
