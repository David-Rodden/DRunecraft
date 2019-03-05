package tasks;

import java.util.regex.Pattern;

public class WithdrawStaminaPotion extends Withdrawal {

    public WithdrawStaminaPotion() {
        super("Stamina potion", Pattern.compile("Stamina potion\\(\\d\\)"));
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public String toString() {
        return "Withdrawing stamina potion";
    }
}
