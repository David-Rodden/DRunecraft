package tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import task_structure.TreeTask;

import java.util.regex.Pattern;

public class WithdrawStaminaPotion extends TreeTask {
    private final Pattern staminaPattern;

    public WithdrawStaminaPotion() {
        super(true);
        staminaPattern = Pattern.compile("Stamina potion\\(\\d\\)");
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        Bank.withdraw(item -> item.getName().contains("Stamina potion"), 1);
        Time.sleepUntil(() -> Inventory.contains(staminaPattern), 1500);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Withdrawing stamina potion";
    }
}
