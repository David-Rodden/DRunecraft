package tasks;

import java.util.regex.Pattern;

public class WithdrawPureEssence extends Withdrawal {

    public WithdrawPureEssence() {
        super(Pattern.compile("Pure essence"), false);
    }

    @Override
    public boolean validate() {
        return false;
    }


    @Override
    public String toString() {
        return "Withdrawing essence";
    }
}
