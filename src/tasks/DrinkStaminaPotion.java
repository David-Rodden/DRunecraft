package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import task_structure.TreeTask;

public class DrinkStaminaPotion extends TreeTask {
    public DrinkStaminaPotion() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Item staminaPotion = Inventory.getFirst(item -> item.getName().contains("Stamina potion"));
        if (staminaPotion != null) {
            final int runEnergy = Movement.getRunEnergy();
            staminaPotion.interact("Drink");
            Time.sleepUntil(() -> Movement.getRunEnergy() > runEnergy, 2000);
        }
        return super.execute();
    }

    @Override
    public String toString() {
        return "Drinking stamina potion";
    }
}
