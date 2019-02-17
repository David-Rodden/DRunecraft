package tasks;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class WalkToJiminua extends TreeTask {
    private final TreeScript handler;

    public WalkToJiminua(final TreeScript handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Position generalStorePosition = handler.getNotedPosition("general store");
        if (generalStorePosition == null) return super.execute();
        // Start running if aggressive monsters are nearby
        final Npc monster = Npcs.getNearest(npc -> npc.getCombatLevel() * 2 > Players.getLocal().getCombatLevel());
        if (!Movement.isRunEnabled() && (Movement.getRunEnergy() > 20 || (monster != null && monster.distance() < 5 && Movement.getRunEnergy() > 5))) {
            Movement.toggleRun(true);
            Time.sleepUntil(Movement::isRunEnabled, 1000);
        }
        Movement.walkTo(generalStorePosition);
        Time.sleepUntil(() -> !Movement.isDestinationSet() || Movement.getDestinationDistance() < 5, 5000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Walking to Jiminua";
    }
}
