package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeScript;
import task_structure.TreeTask;
import utils.Pouches;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmptyPouches extends TreeTask {
    private final TreeScript handler;

    public EmptyPouches(final TreeScript handler) {
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
        for (final Pouches pouch : pouches.collect(Collectors.toSet())) {
            final String pouchName = pouch.toString();
            final int freeSlots = Inventory.getFreeSlots();
            if (!handler.getNotedFlag(pouchName) || freeSlots < pouch.getStorageSize()) continue;
            final Item focused = Inventory.getFirst(pouchName);
            if (focused == null) continue;
            handler.setNotedFlag(pouchName, false);
            focused.interact("Empty");
            Time.sleepUntil(() -> Inventory.getFreeSlots() < freeSlots, 1000);
        }
        return super.execute();
    }

    @Override
    public String toString() {
        return "Emptying pouches";
    }
}
