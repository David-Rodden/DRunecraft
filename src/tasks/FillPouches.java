package tasks;

import methods.CraftMethod;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeScript;
import task_structure.TreeTask;
import utils.Pouches;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FillPouches extends TreeTask {
    private final TreeScript handler;

    public FillPouches(final TreeScript handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Stream<Pouches> pouches = Arrays.stream(Pouches.values()).filter(pouch -> handler.getNotedSetting(pouch.toString())).sorted(Comparator.comparing(Pouches::getBitSize).reversed());
        int essenceCount = Inventory.getCount(CraftMethod.PURE_ESSENCE_ID);
        for (final Pouches pouch : pouches.collect(Collectors.toSet())) {
            final String pouchName = pouch.toString();
            final int storageSize = pouch.getStorageSize();
            if (handler.getNotedFlag(pouchName) || essenceCount < storageSize)
                continue;
            final Item focused = Inventory.getFirst(pouchName);
            if (focused == null) continue;
            handler.setNotedFlag(pouchName, true);
            focused.interact("Fill");
            essenceCount -= storageSize;
        }
        final int resultingEssenceCount = essenceCount;
        Time.sleepUntil(() -> Inventory.getCount(CraftMethod.PURE_ESSENCE_ID) == resultingEssenceCount, Random.high(1400, 1800));
        return super.execute();
    }

    @Override
    public String toString() {
        return "Filling empty pouches";
    }
}
