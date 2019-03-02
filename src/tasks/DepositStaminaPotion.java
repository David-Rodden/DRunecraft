package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

import java.util.regex.Pattern;

public class DepositStaminaPotion extends TreeTask {
    private final Pattern staminaPattern;

    public DepositStaminaPotion() {
        super(true);
        staminaPattern = Pattern.compile("(Stamina potion\\(\\d\\)|Vial)");
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        Bank.depositAll(item -> item.getName().matches(staminaPattern.toString()));
        Time.sleepUntil(() -> !Inventory.contains(staminaPattern), Random.high(1300, 1500));
        return super.execute();
    }

    @Override
    public String toString() {
        return "Depositing stamina potion";
    }
}
