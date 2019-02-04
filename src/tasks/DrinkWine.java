package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class DrinkWine extends TreeTask {
    public DrinkWine() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Item wine = Inventory.getFirst(1993);
        if (wine != null) {
            wine.interact("Drink");
            Time.sleepUntil(() -> !Inventory.contains(1993), 3000);
        }
        return super.execute();
    }
}
