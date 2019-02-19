package tasks;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;


public class ShouldUseShortcut extends TreeTask {
    private final TreeScript handler;

    public ShouldUseShortcut(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final int agilityLevel = Skills.getLevel(Skill.AGILITY);
        Position outer = handler.getNotedPosition("high agility outer"), inner = handler.getNotedPosition("high agility inner");
        if (agilityLevel >= 69 && inner != null && outer != null && inner.distance() < outer.distance()) return true;
        outer = handler.getNotedPosition("low agility outer");
        inner = handler.getNotedPosition("low agility inner");
        return agilityLevel >= 52 && inner != null && outer != null && inner.distance() < outer.distance();
    }
}
