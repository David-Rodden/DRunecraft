package tasks;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.scene.Players;
import task_structure.TreeTask;

public class IsNearBank extends TreeTask {
    public IsNearBank() {
        super(false);
    }

    @Override
    public boolean validate() {
        return BankLocation.getNearest().getPosition().distance(Players.getLocal()) < 5;
    }
}
