package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Shop;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class BuyEssence extends TreeTask {
    public BuyEssence() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final String essence = "Pure essence";
        final int needed = Inventory.getFreeSlots(), inStock = Shop.getQuantity(essence);
        // Each essence costs 6 coins, so if you don't have enough to dish out, exit script
        if (Inventory.getCount(true, "Coins") < needed * 6) return -1;
        Shop.buyFifty(essence);
        Time.sleepUntil(() -> Shop.getQuantity(essence) < inStock, Random.high(2400, 3100));
        return super.execute();
    }

    @Override
    public String toString() {
        return "Buying essence";
    }
}
