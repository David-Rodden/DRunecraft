package tasks;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import task_structure.TreeTask;

public class IsHealthBelowThreshold extends TreeTask {
    private final int multiplier;

    public IsHealthBelowThreshold() {
        this(1);
    }

    public IsHealthBelowThreshold(final int multiplier) {
        super(false);
        this.multiplier = multiplier;
    }

    @Override
    public boolean validate() {
        return Skills.getCurrentLevel(Skill.HITPOINTS) < Skills.getLevel(Skill.HITPOINTS) * multiplier / 3;
    }
}
