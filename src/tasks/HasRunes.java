package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

import java.util.regex.Pattern;

public class HasRunes extends TreeTask {
    private final Pattern runePattern;

    public HasRunes() {
        super(false);
        runePattern = Pattern.compile("\\D+\\srune");
    }

    @Override
    public boolean validate() {
        return Inventory.contains(runePattern);
    }
}
