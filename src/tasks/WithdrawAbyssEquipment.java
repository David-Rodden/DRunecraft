package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;
import utils.AbyssEquipment;

public class WithdrawAbyssEquipment extends TreeTask {
    private final AbyssEquipment equipment;

    public WithdrawAbyssEquipment(final AbyssEquipment equipment) {
        super(true);
        this.equipment = equipment;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final String missingPiece = equipment.getMissingEquipment();
        Bank.withdraw(missingPiece, 1);
        Time.sleepUntil(() -> Inventory.contains(missingPiece), Random.high(2900, 3300));
        return super.execute();
    }

    @Override
    public String toString() {
        return "Withdrawing equipment";
    }
}
