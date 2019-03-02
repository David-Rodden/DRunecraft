package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
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
        Time.sleepUntil(() -> !Shop.isOpen(), Random.high(1900, 2500));
        return super.execute();
    }

    @Override
    public String toString() {
        return "Closing Jiminua's shop";
    }
}
