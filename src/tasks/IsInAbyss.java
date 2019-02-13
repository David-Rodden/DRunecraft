package tasks;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class IsInAbyss extends TreeTask {
    private final TreeScript handler;

    public IsInAbyss(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Position abyssPosition = handler.getNotedPosition("center abyss");
        return abyssPosition != null && abyssPosition.distance() < 60;
    }
}
