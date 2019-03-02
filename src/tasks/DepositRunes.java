package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class DepositRunes extends TreeTask {
    public DepositRunes() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        Bank.depositAll(item -> item.getName().matches(".+\\srune"));
        Time.sleepUntil(() -> !Inventory.contains(item -> item.getName().matches(".+\\srune")), Random.high(2400, 2800));
        return super.execute();
    }

    @Override
    public String toString() {
        return "Depositing runes";
    }
}
