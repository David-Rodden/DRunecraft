package methods;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;
import tasks.*;
import utils.RuneTypes;

public class ArceuusBlood extends CraftMethod {
    public ArceuusBlood(final TreeScript handler) {
        super(RuneTypes.BLOOD.getId());
        handler.addNotedPosition("dark altar", new Position(1718, 3882));
        handler.addNotedPosition("high agility outer", new Position(1761, 3874));
        handler.addNotedPosition("high agility inner", new Position(1761, 3871));
        handler.addNotedPosition("low agility outer", new Position(1774, 3849));
        handler.addNotedPosition("low agility inner", new Position(1769, 3849));
        handler.addNotedPosition("blood descent outer", new Position(1742, 3854));
        handler.addNotedPosition("center essence", new Position(1765, 3852));
        handler.addNotedPosition("blood altar", RuneTypes.BLOOD.getAltarPosition());
        final TreeTask head = new IsNearBloodAltar(handler);
        TreeTask second = head.setLeft(new HasNecessaryZeahItems());
        TreeTask third = second.setLeft(new IsFullOfDenseEssence());
        TreeTask fourth = third.setLeft(new IsNearEssenceMine(handler));
        TreeTask fifth = fourth.setLeft(new ShouldUseShortcut(handler, true));
        fifth.setLeft(new WalkToEssence(handler));
        fifth.setRight(new UseShortcut(handler, true));
        fourth.setRight(new ChipRunestone());
        fourth = third.setRight(new ShouldUseShortcut(handler, false));
        fifth = fourth.setLeft(new IsNearDarkAltar(handler));
        fifth.setLeft(new WalkToDarkAltar(handler));
        fifth.setRight(new VenerateAltar());
        fourth.setRight(new UseShortcut(handler, false));
        second.setRight(new WalkToBloodAltar(handler));
        second = head.setRight(new HasDarkEssenceFragments());
        third = second.setLeft(new HasDarkEssence());
        fourth = third.setLeft(new ShouldUseBloodDescent());
        fourth.setLeft(new WalkToDarkAltar(handler));
        fifth = fourth.setRight(new IsNearBloodDescent(handler));
        fifth.setLeft(new WalkToBloodDescent(handler));
        fifth.setRight(new UseBloodDescent(handler));
        third.setRight(new FragmentizeBlocks());
        third = second.setRight(new IsNextToBloodAltar(handler));
        third.setLeft(new WalkToBloodAltar(handler));
        third.setRight(new BindBloodAltar());
        setHead(head);
    }
}
