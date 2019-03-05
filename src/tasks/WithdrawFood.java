package tasks;

import java.util.regex.Pattern;

public class WithdrawFood extends Withdrawal {


    public WithdrawFood(final String foodType) {
        super(foodType, Pattern.compile(foodType));
    }

    @Override
    public int execute() {
        return super.execute();
    }

    @Override
    public String toString() {
        return "Withdrawing food";
    }
}
