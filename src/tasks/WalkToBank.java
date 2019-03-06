package tasks;

import org.jetbrains.annotations.NotNull;
import org.rspeer.runetek.api.commons.BankLocation;

public class WalkToBank extends WalkToSpecified {
    private final BankLocation specifiedBank;

    public WalkToBank(@NotNull final BankLocation specifiedBank) {
        super(specifiedBank.getPosition());
        this.specifiedBank = specifiedBank;
    }

    public WalkToBank() {
        super();
        specifiedBank = null;
    }

    @Override
    public int execute() {
        return specifiedBank != null ? super.execute() : super.execute(BankLocation.getNearest().getPosition());
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public String toString() {
        return "Walking to bank";
    }
}
