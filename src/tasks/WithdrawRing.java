package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class WithdrawRing extends TreeTask {
    public WithdrawRing() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        for (int charge = 8; charge >= 2; charge--) {
            final String ring = "Ring of dueling(" + charge + ')';
            if (!Bank.contains(ring)) continue;
            Bank.withdraw(ring, 1);
            Time.sleepUntil(() -> Inventory.contains(ring), 4000);
            return super.execute();
        }
        // Fail if no rings left
        return -1;
    }
}
