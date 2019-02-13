package tasks;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class IsNearClanWars extends TreeTask {
    private final TreeScript handler;

    public IsNearClanWars(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Position clanWarsPosition = handler.getNotedPosition("clan wars");
        return clanWarsPosition != null && clanWarsPosition.distance() < 60;
    }
}
