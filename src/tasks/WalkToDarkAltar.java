package tasks;

import task_structure.TreeScript;

public class WalkToDarkAltar extends WalkToSpecified {
    private final TreeScript handler;

    public WalkToDarkAltar(final TreeScript handler) {
        super(handler.getNotedPosition("dark altar"));
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }
}
