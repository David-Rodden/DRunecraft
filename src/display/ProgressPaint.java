package display;

import methods.CraftMethod;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ProgressPaint {
    private final Font progressFont;
    private final StopWatch runtime;
    private Image progressPaint, runePaint;
    private final int runeCost, essenceCost, startExperience;
    private int runes, essence;

    public ProgressPaint(final CraftMethod method) {
        try {
            progressPaint = new ImageIcon(new URL("https://i.imgur.com/9TDsWNo.gif")).getImage();
            runePaint = new ImageIcon(new URL("https://rsbuddy.com/items/554.png")).getImage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        runeCost = method.getRunePrice();
        essenceCost = method.getEssencePrice();
        startExperience = Skills.getExperience(Skill.RUNECRAFTING);
        progressFont = new Font("High Tower Text", Font.PLAIN, 20);
        // Once everything is set, start our script timer
        runtime = StopWatch.start();
    }

    public void updateRunes(final int added) {
        runes += added;
    }

    private void updateEssence(final int used) {
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
