package tasks;

import task_structure.TreeScript;
import task_structure.TreeTask;

public class ShouldTakeMedShortcut extends TreeTask {
    private final TreeScript handler;

    public ShouldTakeMedShortcut(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }
}
