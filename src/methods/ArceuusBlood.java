package methods;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;
import task_structure.TreeTask;
import tasks.*;
import utils.RuneTypes;

public class ArceuusBlood extends CraftMethod {
    public ArceuusBlood(final TreeScript handler) {
        super(handler, RuneTypes.BLOOD.getId());
        handler.addNotedPosition("dark altar", new Position(1718, 3882));
        handler.addNotedPosition("high agility outer", new Position(1761, 3874));
        handler.addNotedPosition("high agility inner", new Position(1761, 3871));
        handler.addNotedPosition("low agility outer", new Position(1774, 3849));
        handler.addNotedPosition("low agility inner", new Position(1769, 3849));
        handler.addNotedPosition("top agility outer", new Position(1742, 3854));
        handler.addNotedPosition("center essence", new Position(1765, 3852));
        handler.addNotedPosition("blood altar", RuneTypes.BLOOD.getAltarPosition());
        final TreeTask head = new IsNearBloodAltar(handler);
        TreeTask second = head.setLeft(new HasNecessaryZeahItems());
        TreeTask third = second.setLeft(new IsFullOfDarkEssence());
        TreeTask fourth = third.setLeft(new IsFullOfDenseEssence());
        TreeTask fifth = fourth.setLeft(new IsNearEssenceMine(handler));
        TreeTask sixth = fifth.setLeft(new ShouldUseShortcut(handler, true));
        sixth.setLeft(new WalkToEssence(handler));
        sixth.setRight(new UseShortcut(handler, true));
        fifth.setRight(new ChipRunestone());
        fifth = fourth.setRight(new ShouldUseShortcut(handler, false));
        sixth = fifth.setLeft(new IsNearDarkAltar(handler));
        sixth.setLeft(new WalkToDarkAltar(handler));
        sixth.setRight(new VenerateAltar());
        fifth.setRight(new UseShortcut(handler, false));
        third.setRight(new FragmentizeBlocks());
        second.setRight(new WalkToBloodAltar(handler));
        second = head.setRight(new HasDarkEssenceFragments());
        second.setLeft(new WalkToDarkAltar(handler));
        second.setRight(new BindBloodAltar());
        setHead(head);
    }
}
