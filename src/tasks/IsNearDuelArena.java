package tasks;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class IsNearDuelArena extends TreeTask {
    private final TreeScript handler;

    public IsNearDuelArena(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Position duelArenaPosition = handler.getNotedPosition("duel arena");
        return duelArenaPosition != null && duelArenaPosition.distance() < 10;
    }
}
