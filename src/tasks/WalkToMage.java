package tasks;

import task_structure.TreeScript;

public class WalkToMage extends WildernessWalkToSpecified {

    public WalkToMage(final TreeScript handler) {
        super(handler);
    }

    @Override
    public String toString() {
        return "Walking to Mage of Zamorak";
    }
}
