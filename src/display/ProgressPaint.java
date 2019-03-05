package display;

import methods.CraftMethod;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class ProgressPaint {
    private final static int PAINT_ORIGIN_X = 6, PAINT_ORIGIN_Y = 344, PAINT_WIDTH = 507, PAINT_HEIGHT = 129;
    private final Font progressFont;
    private final StopWatch runtime;
    private Image progressPaint, runePaint;
    private final int runeCost, essenceCost, startExperience;
    private int runes, essence;
    private final Color timeColor, runeColor, xpColor, gpColor;
    private boolean visible;

    public ProgressPaint(final CraftMethod method) {
        try {
            progressPaint = new ImageIcon(new URL("https://i.imgur.com/rUne8Pt.gif")).getImage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        runePaint = method.getRuneIcon();
        runeCost = method.getRunePrice();
        essenceCost = method.getEssencePrice();
        startExperience = Skills.getExperience(Skill.RUNECRAFTING);
        progressFont = Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()).contains("High Tower Text") ?
                new Font("High Tower Text", Font.PLAIN, 20) : new Font("Constantia", Font.PLAIN, 18);
        timeColor = new Color(201, 201, 201);
        runeColor = new Color(97, 199, 201);
        xpColor = new Color(221, 130, 224);
        gpColor = new Color(133, 193, 91);
        // Once everything is set, start our script timer
        runtime = StopWatch.start();
        visible = true;
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
        if (!visible) return;
        if (progressPaint == null) return;
        graphics.drawImage(progressPaint, PAINT_ORIGIN_X, PAINT_ORIGIN_Y, null);
        graphics.drawImage(runePaint, 33, 418, null);
        graphics.setColor(timeColor);
        graphics.drawString(runtime.toElapsedString(), 60, 375);
        graphics.setColor(runeColor);
        graphics.drawString(String.valueOf(runes), 80, 430);
        final int hourlyRunes = (int) runtime.getHourlyRate(runes);
        graphics.drawString(String.format("%s ph", hourlyRunes >= 10000 ? hourlyRunes / 1000 + "K" : hourlyRunes), 80, 450);
        final int gainedExperience = Skills.getExperience(Skill.RUNECRAFTING) - startExperience, hourlyExperience = (int) runtime.getHourlyRate(gainedExperience);
        graphics.setColor(xpColor);
        graphics.drawString(String.valueOf(gainedExperience), 240, 430);
        graphics.drawString(String.format("%s ph", hourlyExperience >= 10000 ? hourlyExperience / 1000 + "K" : hourlyExperience), 240, 450);
        final int hourlyEssence = (int) runtime.getHourlyRate(essence), hourlyProfit = hourlyRunes * runeCost - hourlyEssence * essenceCost;
        graphics.setColor(gpColor);
        graphics.drawString(String.valueOf(runes * runeCost - essence * essenceCost), 400, 430);
        graphics.drawString(String.format("%s ph", hourlyProfit >= 10000 ? hourlyProfit / 1000 + "K" : hourlyProfit), 400, 450);
    }

    public void displayPaint(final Graphics graphics, final String description) {
        displayPaint(graphics);
        graphics.setColor(Color.WHITE);
        graphics.drawString(description, 200, 200);
    }

    @Override
    public String toString() {
        return String.format("Total Runtime: %s, Experience Gained: %d, Money Accrued: %d", runtime.toElapsedString(), Skills.getExperience(Skill.RUNECRAFTING) - startExperience, runes * runeCost - essence * essenceCost);
    }

    public void toggleIfMet(final Point point) {
        if (progressPaint == null) return;
        final double x = point.getX(), y = point.getY();
        if (x > PAINT_ORIGIN_X && y > PAINT_ORIGIN_Y && x < PAINT_ORIGIN_X + PAINT_WIDTH && y < PAINT_ORIGIN_Y + PAINT_HEIGHT)
            visible = !visible;
    }
}
