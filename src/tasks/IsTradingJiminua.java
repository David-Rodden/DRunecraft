package tasks;

import org.rspeer.runetek.api.component.Shop;
import task_structure.TreeTask;

public class IsTradingJiminua extends TreeTask {
    public IsTradingJiminua() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Shop.isOpen();
    }
}
