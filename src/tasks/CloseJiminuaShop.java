package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Shop;
import task_structure.TreeTask;

public class CloseJiminuaShop extends TreeTask {
    public CloseJiminuaShop() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        Shop.close();
        Time.sleepUntil(() -> !Shop.isOpen(), 2000);
        return super.execute();
    }
}
