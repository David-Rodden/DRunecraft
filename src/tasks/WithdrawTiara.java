package tasks;

import java.util.regex.Pattern;

public class WithdrawTiara extends Withdrawal {
    private final String runeType;

    public WithdrawTiara(final String runeType) {
        super(runeType + " tiara", Pattern.compile(runeType + "\\stiara"));
        this.runeType = runeType;
    }

    @Override
    public String toString() {
        return "Withdrawing " + runeType + " tiara";
    }
}
