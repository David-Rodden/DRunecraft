package tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import task_structure.TreeScript;
import task_structure.TreeTask;

public class UseShortcut extends TreeTask {
    private final TreeScript handler;
    private final boolean isHeadingToEssence;

    public UseShortcut(final TreeScript handler, final boolean isHeadingToEssence) {
        super(true);
        this.handler = handler;
        this.isHeadingToEssence = isHeadingToEssence;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final int agilityLevel = Skills.getLevel(Skill.AGILITY);
        final String keyword = isHeadingToEssence ? "outer" : "inner";
        final Position rockPosition = handler.getNotedPosition((agilityLevel >= ShouldUseShortcut.HIGH_AGILITY ? "high" : "low") + " agility " + keyword);
        final SceneObject shortcut = SceneObjects.getNearest(sceneObject -> sceneObject.getName().equals("Rocks") && sceneObject.containsAction("Climb") && sceneObject.distance(rockPosition) < 2);
        if (shortcut != null) {
            shortcut.interact("Climb");
            Time.sleepUntil(() -> !Players.getLocal().isAnimating() && rockPosition.distance() > 1, 4000);
        }
        return super.execute();
    }

    @Override
    public String toString() {
        return "Using suitable shortcut";
    }
}
