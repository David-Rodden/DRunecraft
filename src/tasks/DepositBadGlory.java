package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class DepositBadGlory extends TreeTask {
    public DepositBadGlory() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Item badGlory = Inventory.getFirst("Amulet of glory");
        if (badGlory != null) {
            Bank.depositAll("Amulet of glory");
            Time.sleepUntil(() -> !Inventory.contains("Amulet of glory"), 2000);
        }
        return super.execute();
    }
}
