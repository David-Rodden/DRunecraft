import display.ProgressPaint;
import methods.CastleWarsFire;
import methods.CraftMethod;
import org.rspeer.runetek.event.listeners.ItemTableListener;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.ItemTableEvent;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.runetek.providers.RSItemDefinition;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import task_structure.TreeScript;

import java.awt.*;

@ScriptMeta(name = "DRunecraft", desc = "Crafts runes", developer = "Dungeonqueer", category = ScriptCategory.RUNECRAFTING, version = 1.1)
public class Runecraft extends TreeScript implements RenderListener, ItemTableListener {
    private ProgressPaint progressPaint;

    @Override
    public void onStart() {
        final CraftMethod craftMethod = new CastleWarsFire(this);
        setHead(craftMethod.getHead());
        progressPaint = new ProgressPaint();
        super.onStart();
    }

    @Override
    public int loop() {
        return traverseTree();
    }

    @Override
    public void notify(final RenderEvent renderEvent) {
        final Graphics source = renderEvent.getSource();
        if (source != null) progressPaint.displayPaint(source);
    }

    @Override
    public void notify(final ItemTableEvent event) {
        if (!getTaskDescription().contains("Crafting")) return;
        final RSItemDefinition objectDefinition = event.getDefinition();
        if (objectDefinition == null) return;
        if (objectDefinition.getName().matches(".+\\srune") && event.getChangeType() == ItemTableEvent.ChangeType.ITEM_ADDED)
            progressPaint.updateRunes(event.getStackSize());
        else if (objectDefinition.getName().contains("essence") && event.getChangeType() == ItemTableEvent.ChangeType.ITEM_REMOVED)
            progressPaint.updateEssence();
    }
}
