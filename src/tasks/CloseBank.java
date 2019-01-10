package tasks;

import org.rspeer.runetek.api.commons.Time;
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
        Time.sleepUntil(Bank::isClosed, 2000);
        return super.execute();
    }
}
