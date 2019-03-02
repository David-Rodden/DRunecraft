package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import task_structure.TreeTask;

public class OpenBank extends TreeTask {
    public OpenBank() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        Bank.open();
        Time.sleepUntil(Bank::isOpen, Random.high(1800, 2400));
        return super.execute();
    }

    @Override
    public String toString() {
        return "Opening bank";
    }
}
