package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

public class WithdrawMindTabs extends TreeTask {
    private final int MAXIMUM_WITHDRAWAL_ATTEMPTS = 3, TAB_CAP = 100;
    private int withdrawAttempts;

    public WithdrawMindTabs() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        if (!Bank.contains("Mind altar teleport")) {
            Time.sleepUntil(() -> Bank.contains("Mind altar teleport"), Random.high(500, 1000));
            withdrawAttempts++;
            return withdrawAttempts == MAXIMUM_WITHDRAWAL_ATTEMPTS ? -1 : super.execute();
        }
        final int tabCount = Bank.getCount("Mind altar teleport");
        Bank.withdraw("Mind altar teleport", tabCount > TAB_CAP ? TAB_CAP : tabCount);
        Time.sleepUntil(() -> Inventory.contains("Mind altar teleport"), Random.high(1800, 2200));
        withdrawAttempts = 0;
        return super.execute();
    }
}
