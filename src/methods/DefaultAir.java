package methods;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;
import tasks.*;

public class DefaultAir extends CraftMethod {
    public DefaultAir(final TreeScript handler) {
        super(handler);
        handler.addNotedPosition("bank", new Position(3012, 3355));
        handler.addNotedPosition("outside altar", new Position(2986, 3294));
        handler.addNotedPosition("inside altar", new Position(2841, 4830));
        final TreeTask head = new HasRunes("Air");
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
