package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class WithdrawGlory extends TreeTask {
    public WithdrawGlory() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        Bank.withdraw(item -> item.getName().contains("Amulet of glory("), 1);
        Time.sleepUntil(() -> Inventory.contains("Amulet of glory("), 2000);
        return super.execute();
    }
}
