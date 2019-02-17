package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Withdrawal extends TreeTask {
    private final static int MAXIMUM_WITHDRAWAL_ATTEMPTS = 3;
    private int withdrawAttempts;
    private final Pattern toWithdraw;
    private final boolean withdrawSingle;

    Withdrawal(final Pattern toWithdraw) {
        this(toWithdraw, true);
    }

    Withdrawal(final Pattern toWithdraw, final boolean withdrawSingle) {
        super(true);
        this.toWithdraw = toWithdraw;
        this.withdrawSingle = withdrawSingle;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        if (!Bank.contains(toWithdraw)) {
            Time.sleepUntil(() -> Bank.contains(toWithdraw), Random.high(500, 1000));
            withdrawAttempts++;
            return withdrawAttempts == MAXIMUM_WITHDRAWAL_ATTEMPTS ? -1 : super.execute();
        }
        final Predicate<? super Item> itemMatch = item -> item.getName().matches(toWithdraw.toString());
        if (withdrawSingle) Bank.withdraw(itemMatch, 1);
        else Bank.withdrawAll(itemMatch);
        Time.sleepUntil(() -> Inventory.contains(toWithdraw), Random.high(1000, 1300));
        withdrawAttempts = 0;
        return super.execute();
    }
}
