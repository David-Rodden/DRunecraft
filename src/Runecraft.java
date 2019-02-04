import display.ProgressPaint;
import display.RunecraftGUI;
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

@ScriptMeta(name = "DRunecraft", desc = "Crafts runes", developer = "Dungeonqueer", category = ScriptCategory.RUNECRAFTING, version = 1.2)
public class Runecraft extends TreeScript implements RenderListener, ItemTableListener {
    private RunecraftGUI runecraftGUI;
    private ProgressPaint progressPaint;

    @Override
    public void onStart() {
        runecraftGUI = new RunecraftGUI();
        super.onStart();
    }

    @Override
    public int loop() {
        if (!runecraftGUI.hasBeenSet()) return 2000;
        if (!isHeadSet()) {
            final CraftMethod craftMethod = runecraftGUI.getMethod(this);
            setHead(craftMethod.getHead());
            progressPaint = new ProgressPaint(craftMethod);
        }
        return traverseTree();
    }

    @Override
    public void onStop() {
        System.out.println("We've stopped for whatever reason");
        System.out.println("Info: " + getTaskDescription());
        super.onStop();
    }

    @Override
    public void notify(final RenderEvent renderEvent) {
        final Graphics source = renderEvent.getSource();
        final String taskDescription;
        if (source == null || progressPaint == null || (taskDescription = getTaskDescription()) == null) return;
//        progressPaint.displayPaint(source);
        progressPaint.displayPaint(source, taskDescription);
    }

    @Override
    public void notify(final ItemTableEvent event) {
        if (!getTaskDescription().contains("Crafting")) return;
        final RSItemDefinition objectDefinition = event.getDefinition();
        if (objectDefinition == null) return;
        if (objectDefinition.getName().matches(".+\\srune") && event.getChangeType() == ItemTableEvent.ChangeType.ITEM_ADDED)
            progressPaint.updateRunes(event.getStackSize() - event.getOldStackSize());
        else if (objectDefinition.getName().contains("essence") && event.getChangeType() == ItemTableEvent.ChangeType.ITEM_REMOVED)
            progressPaint.updateEssence();
    }
}
