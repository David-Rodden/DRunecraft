package tasks;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class IsInAltar extends TreeTask {
    private final TreeScript handler;

    public IsInAltar(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Position altarPosition = handler.getNotedPosition("inside altar");
        return altarPosition != null && altarPosition.distance() < 20;
    }
}
