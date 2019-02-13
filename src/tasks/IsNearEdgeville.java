package tasks;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class IsNearEdgeville extends TreeTask {
    private final TreeScript handler;

    public IsNearEdgeville(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Position edgevillePosition = handler.getNotedPosition("edgeville");
        return edgevillePosition != null && edgevillePosition.distance() < 130;
    }
}
