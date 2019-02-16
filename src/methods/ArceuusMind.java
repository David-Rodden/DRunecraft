package methods;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;
import tasks.*;
import utils.RuneTypes;

public class ArceuusMind extends CraftMethod {
    public ArceuusMind(final TreeScript handler) {
        super(handler, RuneTypes.MIND.getId());
        handler.addNotedPosition("castle wars", new Position(2440, 3089));
        handler.addNotedPosition("outside altar", new Position(2980, 3512));
        handler.addNotedPosition("inside altar", RuneTypes.MIND.getAltarPosition());
        final TreeTask head = new HasRunes(RuneTypes.MIND.toString());
        TreeTask second = head.setLeft(new HasPureEssence());
        TreeTask third = second.setLeft(new IsBankOpen());
        TreeTask fourth = third.setLeft(new IsNearBank());
        TreeTask fifth = fourth.setLeft(new IsNearCastlewars(handler));
        fifth.setLeft(new TeleportToCastleWars(handler));
        fifth.setRight(new WalkToBank(BankLocation.CASTLE_WARS));
        fifth = fourth.setRight(new IsRingInInventory());
        fifth.setLeft(new OpenBank());
        fifth.setRight(new EquipRing());
        fourth = third.setRight(new IsRingEquipped());
        fifth = fourth.setLeft(new IsRingInInventory());
        fifth.setLeft(new WithdrawRing());
        TreeTask sixth = fifth.setRight(new IsMindTabInInventory());
        sixth.setLeft(new WithdrawMindTabs());
        sixth.setRight(new CloseBank());
        fourth.setRight(new WithdrawPureEssence());
        third = second.setRight(new IsInAltar(handler));
        fourth = third.setLeft(new IsNearRuins(handler));
        fifth = fourth.setLeft(new HasArceuusItems());
        sixth = fifth.setLeft(new IsBankOpen());
        sixth.setLeft(new OpenBank());
        TreeTask seventh = sixth.setRight(new IsMindTabInInventory());
        TreeTask eighth = seventh.setLeft(new HasEnoughEssence());
        eighth.setLeft(new WithdrawMindTabs());
        eighth.setRight(new DepositPureEssence());
        eighth = seventh.setRight(new IsRingEquipped());
        TreeTask ninth = eighth.setLeft(new IsRingInInventory());
        TreeTask tenth = ninth.setLeft(new HasEnoughEssence());
        tenth.setLeft(new WithdrawRing());
        tenth.setRight(new DepositPureEssence());
        ninth.setRight(new EquipRing());
        eighth.setRight(new WithdrawPureEssence());
        fifth.setRight(new TeleportToMindAltar(handler));
        fourth.setRight(new EnterRuins(handler));
        third.setRight(new CraftRunes(handler));
        second = head.setRight(new IsBankOpen());
        third = second.setLeft(new IsNearBank());
        fourth = third.setLeft(new IsNearCastlewars(handler));
        fourth.setLeft(new TeleportToCastleWars(handler));
        fourth.setRight(new WalkToBank(BankLocation.CASTLE_WARS));
        third.setRight(new OpenBank());
        second.setRight(new DepositRunes());
        setHead(head);
    }
}
