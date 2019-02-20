package tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import task_structure.TreeScript;
import task_structure.TreeTask;

import java.util.Arrays;

public class UseShortcut extends TreeTask {
    private final TreeScript handler;

    public UseShortcut(final TreeScript handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final int agilityLevel = Skills.getLevel(Skill.AGILITY);
        final Position high = handler.getNotedPosition("high agility inner"), low = handler.getNotedPosition("low agility inner");
        final SceneObject[] shortcuts = SceneObjects.getLoaded(sceneObject -> {
            if (high == null || low == null) return false;
            return sceneObject.getName().equals("Rock") && sceneObject.containsAction("Climb") && (agilityLevel >= 67 && sceneObject.distance(high) < 2) ||
                    (agilityLevel >= 52 && sceneObject.distance(low) < 2);
        });
        if (shortcuts == null || shortcuts.length == 0) return super.execute();
        final SceneObject shortcut = Arrays.stream(shortcuts).min((first, second) -> (int) first.distance(high)).get();
        shortcut.interact("Climb");
        Time.sleepUntil(() -> !Players.getLocal().isAnimating() && !Movement.isDestinationSet(), 4000);
        return super.execute();
    }
}
