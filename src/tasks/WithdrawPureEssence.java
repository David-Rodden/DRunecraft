package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class WithdrawPureEssence extends TreeTask {
    public WithdrawPureEssence() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        // Fail if no essence left in bank
        if (!Bank.contains("Pure essence")) return -1;
        Bank.withdraw("Pure essence", Inventory.getFreeSlots());
        Time.sleepUntil(() -> Inventory.contains("Pure essence"), 4000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Withdrawing essence";
    }
}
