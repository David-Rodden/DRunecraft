package tasks;

import methods.CraftMethod;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeScript;
import task_structure.TreeTask;
import utils.Pouches;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DoPouchesNeedFilling extends TreeTask {
    private static final int POUCH_BIT = 261;
    private final TreeScript handler;
    private final Map<String, Boolean> pouches;

    public DoPouchesNeedFilling(final TreeScript handler) {
        super(false);
        this.handler = handler;
        pouches = new HashMap<>();
    }

    @Override
    public boolean validate() {
        for (final Pouches pouch : Arrays.stream(Pouches.values()).filter(pouch -> handler.getNotedSetting(pouch.toString())).collect(Collectors.toSet()))
            if (!handler.getNotedFlag(pouch.toString()) && Inventory.getCount(CraftMethod.PURE_ESSENCE_ID) >= pouch.getStorageSize())
                return true;
        return false;
    }
}
