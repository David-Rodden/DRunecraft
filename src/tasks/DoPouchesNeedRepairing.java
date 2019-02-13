package tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeScript;
import task_structure.TreeTask;
import utils.Pouches;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class DoPouchesNeedRepairing extends TreeTask {
    private final TreeScript handler;

    public DoPouchesNeedRepairing(final TreeScript handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        final Set<Pouches> pouches = Arrays.stream(Pouches.values()).filter(pouch -> {
            final String pouchName = pouch.toString();
            return handler.getNotedSetting(pouchName) && Inventory.contains(pouchName);
        }).collect(Collectors.toSet());
        for (final Pouches pouch : pouches)
            if (!Inventory.contains(pouch.getId())) return true;
        return false;
    }
}
