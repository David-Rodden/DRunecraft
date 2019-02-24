package tasks;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class ShouldUseBloodDescent extends TreeTask {

    public ShouldUseBloodDescent() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Skills.getLevel(Skill.AGILITY) >= 73;
    }
}
