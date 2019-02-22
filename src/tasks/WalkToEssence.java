package tasks;

import task_structure.TreeScript;

public class WalkToEssence extends WalkToSpecified {
    public WalkToEssence(final TreeScript handler) {
        super(handler.getNotedPosition("center essence"));
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public String toString() {
        return "Walking to dense essence mine";
    }
}
