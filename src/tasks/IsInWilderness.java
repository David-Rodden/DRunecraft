package tasks;

import org.rspeer.runetek.api.scene.Players;
import task_structure.TreeTask;

public class IsInWilderness extends TreeTask {
    public IsInWilderness() {
        super(false);
    }

    @Override
    public boolean validate() {
        return Players.getLocal().getY() >= WildernessWalkToSpecified.WILDERNESS_ENTRANCE_Y;
    }
}
