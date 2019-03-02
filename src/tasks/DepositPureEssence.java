package tasks;

import methods.CraftMethod;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class DepositPureEssence extends TreeTask {
    public DepositPureEssence() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final int essenceCount = Inventory.getCount(CraftMethod.PURE_ESSENCE_ID);
        Bank.deposit(CraftMethod.PURE_ESSENCE_ID, 1);
        Time.sleepUntil(() -> Inventory.getCount(CraftMethod.PURE_ESSENCE_ID) < essenceCount, Random.high(1200, 1500));
        return super.execute();
    }

    @Override
    public String toString() {
        return "Depositing pure essence";
    }
}
