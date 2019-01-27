package tasks;

import org.rspeer.runetek.api.commons.BankLocation;

public class WalkToBank extends WalkToSpecified {
    public WalkToBank(final BankLocation specifiedBank) {
        super(specifiedBank.getPosition());
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
