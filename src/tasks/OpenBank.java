package tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.predicate.Predicates;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.SceneObjects;
import task_structure.TreeTask;

public class OpenBank extends TreeTask {
    public OpenBank() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        Bank.open();
        Time.sleepUntil(Bank::isOpen, 2000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Opening bank";
    }
}
