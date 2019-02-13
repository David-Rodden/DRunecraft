package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeScript;
import task_structure.TreeTask;
import utils.Pouches;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class WithdrawPouches extends TreeTask {
    private final TreeScript handler;

    public WithdrawPouches(final TreeScript handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Set<Pouches> pouches = Arrays.stream(Pouches.values()).filter(pouch -> {
            final String pouchName = pouch.toString();
            return handler.getNotedSetting(pouchName) && !Inventory.contains(pouchName);
        }).collect(Collectors.toSet());
        for (final Pouches pouch : pouches) {
            final String pouchName = pouch.toString();
            Bank.withdraw(pouchName, 1);
            Time.sleepUntil(() -> Inventory.contains(pouchName), 3000);
        }
        return super.execute();
    }
}
