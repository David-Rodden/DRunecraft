package tasks;

import task_structure.TreeTask;

public class ShouldSellWine extends TreeTask {
    // TODO: 1/13/2019
    public ShouldSellWine() {
        super(false);
    }

    @Override
    public boolean validate() {
        return false;
    }
}
