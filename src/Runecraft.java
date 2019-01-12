import display.ProgressPaint;
import methods.CraftMethod;
import methods.SimpleWater;
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
        // Current initialization of crafting method is manual - will be replaced by GUI selection in near-future
        final CraftMethod craftMethod = new SimpleWater(this);
        // Initialized craft method with fire rune method
        setHead(craftMethod.getHead());
        progressPaint = new ProgressPaint(craftMethod);
        super.onStart();
    }

    @Override
    public int loop() {
        return traverseTree();
    }

    @Override
    public void notify(final RenderEvent renderEvent) {
        final Graphics source = renderEvent.getSource();
        if (source == null) return;
        progressPaint.displayPaint(source);
//        final String taskDescription = getTaskDescription();
//        if (taskDescription == null) return;
//        source.drawString(taskDescription, 100, 300);
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
