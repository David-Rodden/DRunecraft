package tasks;

import methods.CraftMethod;
import org.rspeer.runetek.api.Definitions;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Shop;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class SellEssence extends TreeTask {
    public SellEssence() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final int essenceStack = Definitions.getItem(CraftMethod.PURE_ESSENCE_ID).getNotedId(), needed = Inventory.getFreeSlots(), inStock = Shop.getQuantity("Pure essence");
        if (needed > 10) Shop.sellTen(essenceStack);
        else if (needed > 5) Shop.sellFive(essenceStack);
        else Shop.sellOne(essenceStack);
        Time.sleepUntil(() -> Shop.getQuantity("Pure essence") > inStock, 4000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Selling essence";
    }
}
