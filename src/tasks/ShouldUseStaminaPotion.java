package tasks;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.movement.Movement;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class ShouldUseStaminaPotion extends TreeTask {
    private static final int RUN_THRESHOLD = 30, STAMINA_THRESHOLD = 60, STAMINA_BIT = 1575;
    private final TreeScript handler;

    public ShouldUseStaminaPotion(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final int runEnergy = Movement.getRunEnergy();
        final boolean staminaActive = Varps.get(STAMINA_BIT) != 0;
        return handler.getNotedSetting("stamina") && (runEnergy <= RUN_THRESHOLD || (!staminaActive && runEnergy <= STAMINA_THRESHOLD));
    }
}
