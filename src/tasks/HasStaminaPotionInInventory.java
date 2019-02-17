package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

import java.util.regex.Pattern;

public class HasStaminaPotionInInventory extends TreeTask {
    private final Pattern staminaPattern;

    public HasStaminaPotionInInventory() {
        super(false);
        staminaPattern = Pattern.compile("(Stamina potion\\(\\d\\)|Vial)");
    }

    @Override
    public boolean validate() {
        return Inventory.contains(staminaPattern);
    }
}
