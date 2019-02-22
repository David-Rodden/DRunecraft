package tasks;

import task_structure.TreeScript;

public class WalkToBloodAltar extends WalkToSpecified {
    public WalkToBloodAltar(final TreeScript handler) {
        super(handler.getNotedPosition("blood altar"));
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public String toString() {
        return "Walking to blood altar";
    }
}
