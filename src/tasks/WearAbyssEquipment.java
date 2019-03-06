package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;
import utils.AbyssEquipment;

public class WearAbyssEquipment extends TreeTask {
    private final AbyssEquipment equipment;

    public WearAbyssEquipment(final AbyssEquipment equipment) {
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
        final Item piece = Inventory.getFirst(missingPiece);
        if (piece != null) {
            piece.interact("Wear");
            Time.sleepUntil(() -> Equipment.contains(missingPiece), Random.high(800, 950));
        }
        return super.execute();
    }

    @Override
    public String toString() {
        return "Putting on equipment";
    }
}
