package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

import java.util.regex.Pattern;

public class WithdrawGlory extends TreeTask {
    private final int MAXIMUM_WITHDRAWAL_ATTEMPTS = 3;
    private int withdrawAttempts;
    private final Pattern gloryPattern;

    public WithdrawGlory() {
        super(true);
        gloryPattern = Pattern.compile("Amulet of glory\\(\\d\\)");
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        if (!Bank.contains(gloryPattern)) {
            Time.sleepUntil(() -> Bank.contains(gloryPattern), Random.high(500, 1000));
            withdrawAttempts++;
            return withdrawAttempts == MAXIMUM_WITHDRAWAL_ATTEMPTS ? -1 : super.execute();
        }
        Bank.withdraw(item -> item.getName().contains("Amulet of glory("), 1);
        Time.sleepUntil(() -> Inventory.contains(gloryPattern), Random.high(1800, 2200));
        withdrawAttempts = 0;
        return super.execute();
    }
}
