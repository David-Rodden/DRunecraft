package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import task_structure.TreeTask;

public class CloseBank extends TreeTask {
    public CloseBank() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        Bank.close();
        Time.sleepUntil(Bank::isClosed, Random.high(1900, 2400));
        return super.execute();
    }

    @Override
    public String toString() {
        return "Closing bank";
    }
}
