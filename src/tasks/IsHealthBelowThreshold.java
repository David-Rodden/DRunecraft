package tasks;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import task_structure.TreeTask;

public class IsHealthBelowThreshold extends TreeTask {
    public IsHealthBelowThreshold() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Skills.getCurrentLevel(Skill.HITPOINTS) < Skills.getLevel(Skill.HITPOINTS) / 3;
    }
}
