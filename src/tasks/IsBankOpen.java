package tasks;

import org.rspeer.runetek.api.component.Bank;
import task_structure.TreeTask;

public class IsBankOpen extends TreeTask {
    public IsBankOpen() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Bank.isOpen();
    }
}
