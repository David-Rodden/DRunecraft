package methods;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;
import tasks.*;

public class GeneralStoreNature extends CraftMethod {

    public GeneralStoreNature(final TreeScript handler) {
        super(handler, CraftMethods.GENERAL_STORE_NATURE.getId());
        handler.addNotedPosition("general store", new Position(2768, 3120));
        handler.addNotedPosition("outside altar", new Position(2867, 3020));
        handler.addNotedPosition("inside altar", new Position(2400, 4837));
        final TreeTask head = new HasEnoughEssence();
        TreeTask second = head.setLeft(new IsTradingJiminua());
        TreeTask third = second.setLeft(new IsNearJiminua(handler));
        TreeTask fourth = third.setLeft(new IsInAltar(handler));
        fourth.setLeft(new WalkToJiminua(handler));
        fourth.setRight(new ExitAltar(handler));
        third.setRight(new TradeJiminua());
        third = second.setRight(new HasEssenceInShop());
        third.setLeft(new SellEssence());
        third.setRight(new BuyEssence());
        second = head.setRight(new IsInAltar(handler));
        third = second.setLeft(new IsNearRuins(handler));
        third.setLeft(new WalkToRuins(handler));
        third.setRight(new EnterRuins(handler));
        second.setRight(new CraftRunes(handler));
        setHead(head);
    }
}
