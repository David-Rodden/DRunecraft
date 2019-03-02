package tasks;

import org.rspeer.runetek.api.Definitions;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Shop;
import task_structure.TreeTask;

public class SellWine extends TreeTask {
    public SellWine() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final int wineStack = Definitions.getItem(1993).getNotedId();
        Shop.sellOne(wineStack);
        Time.sleepUntil(() -> Shop.contains("Wine"), Random.high(2800, 3200));
        return super.execute();
    }

    @Override
    public String toString() {
        return "Selling wine";
    }
}
