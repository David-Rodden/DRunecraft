package tasks;

import methods.CraftMethod;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class WithdrawPureEssence extends TreeTask {
    private final int MAXIMUM_WITHDRAWAL_ATTEMPTS = 3;
    private int withdrawAttempts;

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
        if (!Bank.contains("Pure essence")) {
            Time.sleepUntil(() -> Bank.contains("Pure essence"), Random.high(500, 1000));
            withdrawAttempts++;
            return withdrawAttempts == MAXIMUM_WITHDRAWAL_ATTEMPTS ? -1 : super.execute();
        }
        final int essenceCount = Inventory.getCount(CraftMethod.PURE_ESSENCE_ID);
        Bank.withdraw("Pure essence", Inventory.getFreeSlots());
        Time.sleepUntil(() -> Inventory.getCount(CraftMethod.PURE_ESSENCE_ID) > essenceCount, 2000);
        withdrawAttempts = 0;
        return super.execute();
    }

    @Override
    public String toString() {
        return "Withdrawing essence";
    }
}
