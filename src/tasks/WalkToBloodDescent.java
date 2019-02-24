package tasks;

import task_structure.TreeScript;

public class WalkToBloodDescent extends WalkToSpecified {

    public WalkToBloodDescent(final TreeScript handler) {
        super(handler.getNotedPosition("blood descent outer"));
    }

    @Override
    public String toString() {
        return "Walking to blood descent shortcut";
    }
}
