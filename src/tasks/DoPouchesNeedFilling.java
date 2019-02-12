package tasks;

import task_structure.TreeScript;
import task_structure.TreeTask;
import utils.Pouches;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        final Stream<Pouches> pouches = Arrays.stream(Pouches.values()).filter(pouch -> handler.getNotedFlag(pouch.toString())).sorted(Comparator.comparing(Pouches::getBitSize).reversed());
        for (final Pouches pouch : Arrays.stream(Pouches.values()).filter(pouch -> handler.getNotedSetting(pouch.toString())).collect(Collectors.toSet()))
            if (!handler.getNotedFlag(pouch.toString())) return true;
        return false;
    }
}
