package tasks;

import task_structure.TreeScript;

import java.util.regex.Pattern;

public class WithdrawAbyssTool extends Withdrawal {

    public WithdrawAbyssTool(final TreeScript handler) {
        super("Tool", Pattern.compile("\\D+\\s" + (handler.getNotedSetting("pickaxe") ? "pickaxe" : "axe")));
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public String toString() {
        return "Withdrawing tool";
    }
}

