package tasks;

import java.util.regex.Pattern;

public class WithdrawGlory extends Withdrawal {

    public WithdrawGlory() {
        super("Amulet of glory", Pattern.compile("Amulet of glory\\(\\d\\)"));
    }

    @Override
    public int execute() {
        return super.execute();
    }

    @Override
    public String toString() {
        return "Withdrawing charged glory";
    }
}
