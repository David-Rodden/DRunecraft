package tasks;

import task_structure.TreeScript;

public class WalkToRuins extends WalkToSpecified {

    public WalkToRuins(final TreeScript handler) {
        super(handler.getNotedPosition("outside altar"));
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public String toString() {
        return "Walking to ruins";
    }
}
