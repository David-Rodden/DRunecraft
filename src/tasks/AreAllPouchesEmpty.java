package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeScript;
import task_structure.TreeTask;
import utils.Pouches;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AreAllPouchesEmpty extends TreeTask {
    private final TreeScript handler;

    public AreAllPouchesEmpty(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        for (final Pouches pouch : Arrays.stream(Pouches.values()).filter(pouch -> handler.getNotedSetting(pouch.toString())).collect(Collectors.toSet())) {
            if (handler.getNotedFlag(pouch.toString()) && Inventory.getFreeSlots() >= pouch.getStorageSize())
                return false;
        }
        return true;
    }
}
