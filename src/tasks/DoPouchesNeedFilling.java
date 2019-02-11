package tasks;

import methods.CraftMethod;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeScript;
import task_structure.TreeTask;
import utils.Pouches;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DoPouchesNeedFilling extends TreeTask {
    private static final int POUCH_BIT = 261;
    private final TreeScript handler;

    public DoPouchesNeedFilling(final TreeScript handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Stream<Pouches> pouches = Arrays.stream(Pouches.values()).filter(pouch -> handler.getNotedFlag(pouch.toString())).sorted(Comparator.comparing(Pouches::getBitSize).reversed());
        int pouchResult = Varps.get(POUCH_BIT);
        for (final Pouches pouch : pouches.collect(Collectors.toSet())) {
            final int pouchSize = pouch.getBitSize();
            if (pouchSize <= pouchResult) continue;
            final Item focused = Inventory.getFirst(pouch.toString());
            if (focused == null) continue;
            final int essenceCount = Inventory.getCount(CraftMethod.PURE_ESSENCE_ID);  // unnoted pure essence count
            focused.interact("Fill");
            Time.sleepUntil(() -> Inventory.getCount(CraftMethod.PURE_ESSENCE_ID) < essenceCount, 2000);
            pouchResult -= pouchSize;
        }
        return super.execute();
    }
}
