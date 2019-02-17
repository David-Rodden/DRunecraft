package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class EatFood extends TreeTask {
    private final String foodType;

    public EatFood(final String foodType) {
        super(true);
        this.foodType = foodType;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Item food = Inventory.getFirst(foodType);
        if (food != null) {
            food.interact(s -> s.matches("(Eat|Drink)"));
            Time.sleepUntil(() -> !Inventory.contains(foodType), 2000);
        }
        return super.execute();
    }
}
