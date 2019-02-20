package tasks;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;
import utils.Pathing;


public class ShouldUseShortcut extends TreeTask {
    private final TreeScript handler;
    private final boolean isHeadingToEssence;

    public ShouldUseShortcut(final TreeScript handler, final boolean isHeadingToEssence) {
        super(false);
        this.handler = handler;
        this.isHeadingToEssence = isHeadingToEssence;
    }

    @Override
    public boolean validate() {
        final int agilityLevel = Skills.getLevel(Skill.AGILITY);
        final Position destination = isHeadingToEssence ? handler.getNotedPosition("center essence") : handler.getNotedPosition("dark altar");
        if (destination == null) return false;
        final int distanceToDestination = Pathing.getTrueDistance(destination);
        return distanceToDestination >= 150 && agilityLevel >= 52;
    }
}
