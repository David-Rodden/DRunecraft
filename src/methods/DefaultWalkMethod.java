package methods;

import org.rspeer.runetek.api.commons.BankLocation;
import task_structure.TreeScript;
import task_structure.TreeTask;
import tasks.*;

class DefaultWalkMethod extends CraftMethod {
    private final TreeScript handler;
    private final String runeType;

    DefaultWalkMethod(final TreeScript handler, final int id, final String runeType) {
        super(id);
        this.handler = handler;
        this.runeType = runeType;
    }

    void buildTree(final BankLocation bankLocation) {
        final TreeTask head = new HasRunes();
        TreeTask second = head.setLeft(new HasPureEssenceAndTiara(runeType));
        TreeTask third = second.setLeft(new IsBankOpen());
        TreeTask fourth = third.setLeft(new IsNearBank());
        fourth.setLeft(new WalkToBank(bankLocation));
        fourth.setRight(new OpenBank());
        fourth = third.setRight(new IsTiaraEquipped(runeType));
        TreeTask fifth = fourth.setLeft(new HasTiaraInInventory(runeType));
        fifth.setLeft(new WithdrawTiara(runeType));
        fifth.setRight(new EquipTiara(runeType));
        fifth = fourth.setRight(new ShouldUseStaminaPotion(handler));
        TreeTask sixth = fifth.setLeft(new HasStaminaPotionInInventory(true));
        sixth.setLeft(new WithdrawPureEssence());
        sixth.setRight(new DepositStaminaPotion());
        sixth = fifth.setRight(new HasStaminaPotionInInventory());
        sixth.setLeft(new WithdrawStaminaPotion());
        sixth.setRight(new DrinkStaminaPotion());
        third = second.setRight(new IsInAltar(handler));
        fourth = third.setLeft(new IsNearRuins(handler));
        fourth.setLeft(new WalkToRuins(handler));
        fourth.setRight(new EnterRuins(handler));
        third.setRight(new CraftRunes());
        second = head.setRight(new IsBankOpen());
        third = second.setLeft(new IsNearBank());
        fourth = third.setLeft(new IsInAltar(handler));
        fourth.setLeft(new WalkToBank(bankLocation));
        fourth.setRight(new ExitAltar(handler));
        third.setRight(new OpenBank());
        second.setRight(new DepositRunes());
        setHead(head);
    }
}
