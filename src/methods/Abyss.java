package methods;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;
import tasks.*;
import utils.AbyssObstacles;

public class Abyss extends CraftMethod {
    public Abyss(final TreeScript handler) {
        super(handler, CraftMethods.ABYSS.getId());
        final int miniquestCompleted = Varps.get(491);  //1073799168?
        handler.addNotedPosition("edgeville", new Position(3088, 3502));
        handler.addNotedPosition("mage", new Position(3107, 3557));
        handler.addNotedPosition("center abyss", new Position(3037, 4830));
        handler.addNotedPosition("outer ffa", new Position(3352, 3164));
        handler.addNotedPosition("inner ffa", new Position(3327, 4751));
        handler.addNotedPosition("clan wars area", new Position(3369, 3170));
        handler.addNotedPosition("inside altar", new Position(2400, 4837));
        for (final AbyssObstacles obstacle : AbyssObstacles.getMiningLoadout())
            handler.addNotedSetting(obstacle.toString());
        final TreeTask head = new HasRunes("Nature");
        TreeTask second = head.setLeft(new HasPureEssence());
        TreeTask third = second.setLeft(new IsInAltar(handler));
        TreeTask fourth = third.setLeft(new IsBankOpen());
        TreeTask fifth = fourth.setLeft(new IsNearBank());
        TreeTask sixth = fifth.setLeft(new IsNearClanWars(handler));
        sixth.setLeft(new TeleportToClanWars(handler));
        sixth.setRight(new WalkToBank(BankLocation.CLAN_WARS));
        fifth.setLeft(new OpenBank());
        fifth = fourth.setRight(new ArePouchesInInventory());
        fifth.setLeft(new WithdrawPouches());
        sixth = fifth.setRight(new IsRingEquipped());
        TreeTask seventh = sixth.setLeft(new IsRingInInventory());
        seventh.setLeft(new WithdrawRing());
        sixth = sixth.setRight(new IsGloryEquipped());
        seventh.setRight(sixth);
        seventh = sixth.setLeft(new IsGloryInInventory());
        seventh.setLeft(new WithdrawGlory());
        sixth = sixth.setRight(new WithdrawPureEssence());
        seventh.setRight(sixth);
        fourth = third.setRight(new DoPouchesNeedFilling(handler));
        fourth.setLeft(new EmptyPouches(handler));
        fourth.setRight(new TeleportToClanWars(handler));
        third = second.setRight(new IsBankOpen());
        fourth = third.setLeft(new DoPouchesNeedFilling(handler));
        fifth = fourth.setLeft(new IsInAbyss());
        sixth = fifth.setLeft(new IsInAltar(handler));
        seventh = sixth.setLeft(new IsNearEdgeville());
        TreeTask eighth = seventh.setLeft(new IsInFFA());
        TreeTask ninth = eighth.setLeft(new IsNearClanWars(handler));
        ninth.setLeft(new TeleportToClanWars(handler));
        ninth.setRight(new EnterFFA());
        eighth.setRight(new TeleportToEdgeville(handler));
        seventh.setRight(new WalkToMage(handler));
        sixth.setRight(new CraftRunes(handler));
        sixth = fifth.setRight(new IsInInnerAbyss());
        sixth.setLeft(new TraverseObstacle(handler));
        seventh = sixth.setRight(new DoPouchesNeedFilling(handler));
        seventh.setLeft(new EnterRift(handler));
        seventh.setRight(new RepairPouches());
        fourth.setRight(new FillPouches(handler));
        third.setRight(new CloseBank());
        second = head.setRight(new IsBankOpen());
        third = second.setLeft(new IsNearBank());
        fourth = third.setLeft(new IsNearClanWars(handler));
        fourth.setLeft(new TeleportToClanWars(handler));
        fourth.setRight(new WalkToBank(BankLocation.CLAN_WARS));
        third.setRight(new OpenBank());
        second.setRight(new DepositRunes());
        setHead(head);
    }
}
