package tasks;

import org.rspeer.runetek.api.movement.Movement;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class ShouldUseStaminaPotion extends TreeTask {
    private static final int STAMINA_THRESHOLD = 40, STAMINA_BIT = 1575;
    private final TreeScript handler;

    public ShouldUseStaminaPotion(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return handler.getNotedSetting("stamina") && Movement.getRunEnergy() <= STAMINA_THRESHOLD;
    }
}
