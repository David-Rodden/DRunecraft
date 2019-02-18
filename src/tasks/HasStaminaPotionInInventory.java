package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

import java.util.regex.Pattern;

public class HasStaminaPotionInInventory extends TreeTask {
    private final Pattern staminaPattern;

    public HasStaminaPotionInInventory() {
        this(false);
    }

    public HasStaminaPotionInInventory(final boolean includingVial) {
        super(false);
        staminaPattern = Pattern.compile(includingVial ? "(Stamina potion\\(\\d\\)|Vial)" : "Stamina potion\\(\\d\\)");
    }

    @Override
    public boolean validate() {
        return Inventory.contains(staminaPattern);
    }
}
