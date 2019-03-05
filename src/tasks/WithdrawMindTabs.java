package tasks;

import java.util.regex.Pattern;

public class WithdrawMindTabs extends Withdrawal {

    public WithdrawMindTabs() {
        super("Mind altar teleport", Pattern.compile("Mind altar teleport"));
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public String toString() {
        return "Withdrawing mind teleport tabs";
    }
}
