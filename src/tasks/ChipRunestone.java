package tasks;

import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import task_structure.TreeTask;

public class ChipRunestone extends TreeTask {
    public ChipRunestone() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Player myself = Players.getLocal();
        final SceneObject[] allRuneStones = SceneObjects.getLoaded(sceneObject -> sceneObject.getName().equals("Dense runestone"));
        if (allRuneStones == null) return super.execute();
        final SceneObject runeStone = SceneObjects.getNearest("Dense runestone");
        if (myself == null || myself.isAnimating() || runeStone == null) return super.execute();
        runeStone.interact("Chip");
        Time.sleepUntil(() -> {
            final SceneObject[] currentRuneStones = SceneObjects.getLoaded(sceneObject -> sceneObject.getName().equals("Dense runestone"));
            return currentRuneStones == null || currentRuneStones.length < allRuneStones.length;
        }, 4500);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Chipping dense runestone";
    }
}
