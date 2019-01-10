import methods.CastleWarsFire;
import methods.CraftMethod;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.event.listeners.ItemTableListener;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.ItemTableEvent;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.runetek.providers.RSItemDefinition;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import task_structure.TreeScript;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@ScriptMeta(name = "DRunecraft", desc = "Crafts runes", developer = "Dungeonqueer", category = ScriptCategory.RUNECRAFTING, version = 1.1)
public class Runecraft extends TreeScript implements RenderListener, ItemTableListener {
    private Image progressPaint, runePaint;
    private final Font progressFont;
    private StopWatch runtime;
    private int runes, essence, startExperience, runeCost, essenceCost;

    public Runecraft() {

        // We've added the notable positions, now constructing the task tree
        final CraftMethod craftMethod = new CastleWarsFire(this);
        setHead(craftMethod.getHead());
        progressFont = new Font("High Tower Text", Font.PLAIN, 20);
    }

    @Override
    public void onStart() {
        runtime = StopWatch.start();
        startExperience = Skills.getExperience(Skill.RUNECRAFTING);
        runeCost = getPrice(556);
        essenceCost = getPrice(7936);
        try {
            progressPaint = new ImageIcon(new URL("https://i.imgur.com/9TDsWNo.gif")).getImage();
            runePaint = new ImageIcon(new URL("https://rsbuddy.com/items/554.png")).getImage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        super.onStart();
    }

    @Override
    public int loop() {
        return traverseTree();
    }

    @Override
    public void notify(final RenderEvent renderEvent) {
        final Graphics source = renderEvent.getSource();
        source.setFont(progressFont);
        source.setColor(Color.ORANGE);
        source.drawString("Current task: " + getTaskDescription(), 50, 200);
        if (progressPaint == null) return;
        source.drawImage(progressPaint, 6, 344, null);
        source.drawImage(runePaint, 48, 353, null);
        source.drawString(runtime.toElapsedString(), 155, 409);
        final int hourlyRunes = (int) runtime.getHourlyRate(runes);
        source.drawString(String.format("%d : %s /hr", runes, hourlyRunes > 1000 ? hourlyRunes / 1000 + "K" : hourlyRunes), 155, 429);
        final int gainedExperience = Skills.getExperience(Skill.RUNECRAFTING) - startExperience, hourlyExperience = (int) runtime.getHourlyRate(gainedExperience);
        source.drawString(String.format("%d : %s /hr", gainedExperience, hourlyExperience > 1000 ? hourlyExperience / 1000 + "K" : hourlyExperience), 155, 449);
        final int hourlyEssence = (int) runtime.getHourlyRate(essence), hourlyProfit = hourlyRunes * runeCost - hourlyEssence * essenceCost;
        source.drawString(String.format("%d : %s GP/hr", runes * runeCost - essence * essenceCost, hourlyProfit > 1000 ? hourlyProfit / 1000 + "K" : hourlyProfit), 155, 469);
    }

    @Override
    public void notify(final ItemTableEvent event) {
        if (!getTaskDescription().contains("Crafting")) return;
        final RSItemDefinition objectDefinition = event.getDefinition();
        if (objectDefinition == null) return;
        if (objectDefinition.getName().matches(".+\\srune") && event.getChangeType() == ItemTableEvent.ChangeType.ITEM_ADDED)
            runes += event.getStackSize();
        else if (objectDefinition.getName().contains("essence") && event.getChangeType() == ItemTableEvent.ChangeType.ITEM_REMOVED)
            essence++;
    }

    private int getPrice(final int id) {

        //Use a buffered reader to get the price data JSON from the OSRS GE API
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://services.runescape.com/m=itemdb_oldschool/api/catalogue/detail.json?item=" + id).openStream()))) {
            //Parse the given string to find the price
            final String raw = reader.readLine().replace(",", "").replace("\"", "").split("price:")[1].split("}")[0];
            return Integer.parseInt(raw);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
