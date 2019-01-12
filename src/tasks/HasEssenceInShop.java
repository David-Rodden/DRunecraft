package tasks;

import org.rspeer.runetek.api.component.Shop;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class HasEssenceInShop extends TreeTask {
    public HasEssenceInShop() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Shop.getQuantity("Pure essence") >= Inventory.getFreeSlots();
    }
}
