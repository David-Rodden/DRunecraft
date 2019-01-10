package tasks;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class IsNearRuins extends TreeTask {
    private final TreeScript handler;

    public IsNearRuins(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Position ruinsPosition = handler.getNotedPosition("outside altar");
        return ruinsPosition != null && ruinsPosition.distance() < 10;
    }
}
