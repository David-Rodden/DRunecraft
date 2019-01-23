package methods;

import task_structure.TreeScript;
import task_structure.TreeTask;
import tasks.*;

class DefaultWalkMethod extends CraftMethod {
    private final TreeScript handler;
    private final String runeType;

    DefaultWalkMethod(final TreeScript handler, final int id, final String runeType) {
        super(handler, id);
        this.handler = handler;
        this.runeType = runeType;
    }

    void buildTree() {
        final TreeTask head = new HasRunes(runeType);
        TreeTask second = head.setLeft(new HasPureEssence());
        TreeTask third = second.setLeft(new IsBankOpen());
        TreeTask fourth = third.setLeft(new IsNearBank());
        fourth.setLeft(new WalkToBank());
        fourth.setRight(new OpenBank());
        third.setRight(new WithdrawPureEssence());
        third = second.setRight(new IsInAltar(handler));
        fourth = third.setLeft(new IsNearRuins(handler));
        fourth.setLeft(new WalkToRuins(handler));
        fourth.setRight(new EnterRuins(handler));
        third.setRight(new CraftRunes(handler));
        second = head.setRight(new IsBankOpen());
        third = second.setLeft(new IsNearBank());
        fourth = third.setLeft(new IsInAltar(handler));
        fourth.setLeft(new WalkToBank());
        fourth.setRight(new ExitAltar(handler));
        third.setRight(new OpenBank());
        second.setRight(new DepositRunes());
        setHead(head);
    }
}
