package methods;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;
import tasks.*;

public class CastleWarsFire extends CraftMethod {
    public CastleWarsFire(final TreeScript handler) {
        super(handler, 554);
        handler.addNotedPosition("castle wars", new Position(2440, 3089));
        handler.addNotedPosition("duel arena", new Position(3312, 3235));
        handler.addNotedPosition("outside altar", new Position(3312, 3253));
        handler.addNotedPosition("inside altar", new Position(2576, 4848));
        final TreeTask head = new HasRunes("Fire");
        TreeTask second = head.setLeft(new HasPureEssence());
        TreeTask third = second.setLeft(new IsBankOpen());
        TreeTask fourth = third.setLeft(new IsNearBank());
        TreeTask fifth = fourth.setLeft(new IsNearCastlewars(handler));
        fifth.setLeft(new TeleportToCastleWars(handler));
        fifth.setRight(new WalkToBank(BankLocation.CASTLE_WARS));
        fifth = fourth.setRight(new IsRingInInventory());
        fifth.setLeft(new OpenBank());
        fifth.setRight(new EquipRing());
        fourth = third.setRight(new IsBadRingInInventory());
        fifth = fourth.setLeft(new IsRingEquipped());
        TreeTask sixth = fifth.setLeft(new IsRingInInventory());
        sixth.setLeft(new WithdrawRing());
        sixth.setRight(new CloseBank());
        fifth.setRight(new WithdrawPureEssence());
        fourth.setRight(new BankBadRings());
        third = second.setRight(new IsInAltar(handler));
        fourth = third.setLeft(new IsNearRuins(handler));
        fifth = fourth.setLeft(new IsNearDuelArena(handler));
        sixth = fifth.setLeft(new IsBankOpen());
        sixth.setLeft(new TeleportToDuelArena(handler));
        sixth.setRight(new CloseBank());
        fifth.setRight(new WalkToRuins(handler));
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
