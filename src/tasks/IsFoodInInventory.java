package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class IsFoodInInventory extends TreeTask {
    private final String foodType;

    public IsFoodInInventory(final String foodType) {
        super(false);
        this.foodType = foodType;
    }

    @Override
    public boolean validate() {
        return Inventory.contains(foodType);
    }
}
