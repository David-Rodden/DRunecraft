package tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;

import java.util.regex.Pattern;

public class WithdrawPureEssence extends Withdrawal {

    public WithdrawPureEssence() {
        super("Pure essence", Pattern.compile("Pure essence"), false);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Item unecessaryTiara = Inventory.getFirst(item -> item.getName().contains("tiara"));
        if (unecessaryTiara == null) return super.execute();
        final String unecessaryTiaraName = unecessaryTiara.getName();
        Bank.depositAll(unecessaryTiaraName);
        Time.sleepUntil(() -> !Inventory.contains(unecessaryTiaraName), Random.high(800, 1200));
        return super.execute();
    }

    @Override
    public String toString() {
        return "Withdrawing essence";
    }
}
