package display;

import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ProgressPaint {
    private final Font progressFont;
    private final StopWatch runtime;
    private Image progressPaint, runePaint;
    private final int runeCost, essenceCost, startExperience;
    private int runes, essence;

    public ProgressPaint() {
        try {
            progressPaint = new ImageIcon(new URL("https://i.imgur.com/9TDsWNo.gif")).getImage();
            runePaint = new ImageIcon(new URL("https://rsbuddy.com/items/554.png")).getImage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        runeCost = getPrice(556);
        essenceCost = getPrice(7936);
        startExperience = Skills.getExperience(Skill.RUNECRAFTING);
        progressFont = new Font("High Tower Text", Font.PLAIN, 20);
        // Once everything is set, start our script timer
        runtime = StopWatch.start();
    }

    private int getPrice(final int id) {
        //Use a buffered reader to get the price data JSON from the OSRS GE API
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://services.runescape.com/m=itemdb_oldschool/api/catalogue/detail.json?item=" + id).openStream()))) {
            final String raw = reader.readLine().replace(",", "").replace("\"", "").split("price:")[1].split("}")[0];
            return Integer.parseInt(raw);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateRunes(final int added) {
        runes += added;
    }

    public void updateEssence(final int used) {
        essence += used;
    }

    public void updateEssence() {
        updateEssence(1);
    }

    public void displayPaint(final Graphics graphics) {
        graphics.setFont(progressFont);
        graphics.setColor(Color.ORANGE);
        if (progressPaint == null) return;
        graphics.drawImage(progressPaint, 6, 344, null);
        graphics.drawImage(runePaint, 48, 353, null);
        graphics.drawString(runtime.toElapsedString(), 155, 409);
        final int hourlyRunes = (int) runtime.getHourlyRate(runes);
        graphics.drawString(String.format("%d : %s /hr", runes, hourlyRunes > 1000 ? hourlyRunes / 1000 + "K" : hourlyRunes), 155, 429);
        final int gainedExperience = Skills.getExperience(Skill.RUNECRAFTING) - startExperience, hourlyExperience = (int) runtime.getHourlyRate(gainedExperience);
        graphics.drawString(String.format("%d : %s /hr", gainedExperience, hourlyExperience > 1000 ? hourlyExperience / 1000 + "K" : hourlyExperience), 155, 449);
        final int hourlyEssence = (int) runtime.getHourlyRate(essence), hourlyProfit = hourlyRunes * runeCost - hourlyEssence * essenceCost;
        graphics.drawString(String.format("%d : %s GP/hr", runes * runeCost - essence * essenceCost, hourlyProfit > 1000 ? hourlyProfit / 1000 + "K" : hourlyProfit), 155, 469);

    }
}
