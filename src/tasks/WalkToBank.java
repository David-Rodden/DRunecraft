package tasks;

import org.rspeer.runetek.api.commons.BankLocation;

public class WalkToBank extends WalkToSpecified {
    public WalkToBank() {
        super(BankLocation.getNearest().getPosition());
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
