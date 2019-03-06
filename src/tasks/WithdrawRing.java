package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.ui.Log;
import task_structure.TreeTask;

public class WithdrawRing extends TreeTask {
    private final int MAXIMUM_WITHDRAWAL_ATTEMPTS = 3;
    private int withdrawAttempts;

    public WithdrawRing() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        if (!Bank.contains(item -> item.getName().matches("Ring of dueling\\([2-8]\\)"))) {
            Time.sleepUntil(() -> Bank.contains(item -> item.getName().matches("Ring of dueling\\([2-8]\\)")), Random.high(500, 1000));
            withdrawAttempts++;
            if (withdrawAttempts == MAXIMUM_WITHDRAWAL_ATTEMPTS) {
                Log.severe("We've run out of Rings of dueling!");
                return -1;
            }
            return super.execute();
        }
        for (int charge = 8; charge >= 2; charge--) {
            final String ring = "Ring of dueling(" + charge + ')';
            if (!Bank.contains(ring)) continue;
            Bank.withdraw(ring, 1);
            Time.sleepUntil(() -> Inventory.contains(ring), Random.high(3200, 4100));
            return super.execute();
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Withdrawing usable dueling ring";
    }
}
