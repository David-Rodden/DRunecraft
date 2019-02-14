package tasks;

import task_structure.TreeScript;
import task_structure.TreeTask;

public class IsBeingHunted extends TreeTask {
    private final TreeScript handler;

    public IsBeingHunted(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return handler.getNotedFlag("hunted");
    }
}
