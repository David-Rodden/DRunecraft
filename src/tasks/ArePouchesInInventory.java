package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeScript;
import task_structure.TreeTask;
import utils.Pouches;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ArePouchesInInventory extends TreeTask {
    private final TreeScript handler;

    public ArePouchesInInventory(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Set<Pouches> pouches = Arrays.stream(Pouches.values()).filter(pouch -> handler.getNotedSetting(pouch.toString())).collect(Collectors.toSet());
        for (final Pouches pouch : pouches) if (!Inventory.contains(pouch.toString())) return false;
        return true;
    }
}
