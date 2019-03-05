package methods;

import display.RunecraftGUI;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import task_structure.TreeScript;
import utils.FoodTypes;
import utils.RuneTypes;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class RunecraftTask {
    private final Class<?> craftClass;
    private final String methodName;
    private final boolean usingAbyss, usingClanWars, adNauseam;
    private final Set<String> settings;
    private RuneTypes abyssRuneSpecifier;
    private FoodTypes foodChoice;
    private int levelGoal;

    public RunecraftTask(final RunecraftGUI guiReference) {
        craftClass = guiReference.getCraftClass();
        methodName = guiReference.getMethodName();
        usingAbyss = guiReference.isUsingAbyss();
        usingClanWars = guiReference.isUsingClanWars();
        settings = new HashSet<>();
        if (usingAbyss) {
            if (guiReference.isUsingSmallPouch()) settings.add("Small pouch");
            if (guiReference.isUsingMediumPouch()) settings.add("Medium pouch");
            if (guiReference.isUsingLargePouch()) settings.add("Large pouch");
            if (guiReference.isUsingGiantPouch()) settings.add("Giant pouch");
            settings.addAll(guiReference.getObstacles());
            abyssRuneSpecifier = guiReference.getAbyssRuneSpecifier();
            foodChoice = guiReference.getFoodChoice();
        }
        if (guiReference.isUsingStamina()) settings.add("stamina");
        if (!guiReference.isQueuePanelVisible()) {
            adNauseam = true;
            return;
        }
        adNauseam = guiReference.willRunAdNauseam();
        levelGoal = Integer.parseInt(guiReference.getLevelDefinedInput());
    }

    public CraftMethod getMethod(final TreeScript handler) {
        handler.wipeCachedData();
        for (final String setting : settings) handler.addNotedSetting(setting);
        try {
            return (CraftMethod) (usingAbyss ? usingClanWars ? craftClass.getDeclaredConstructor(TreeScript.class, RuneTypes.class).newInstance(handler, abyssRuneSpecifier) : craftClass.getConstructor(TreeScript.class, RuneTypes.class, FoodTypes.class).newInstance(handler, abyssRuneSpecifier, foodChoice) : craftClass.getDeclaredConstructor(TreeScript.class).newInstance(handler));
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasReachedGoal() {
        return !adNauseam && Skills.getLevel(Skill.RUNECRAFTING) >= levelGoal && (Bank.isOpen() || methodName.matches("Arceuus (blood|soul)"));
    }

    @Override
    public String toString() {
        return methodName + (usingAbyss ? " -> " + abyssRuneSpecifier.toString() : "") + (adNauseam ? " ad nauseam" : " until level " + levelGoal) + (settings.contains("stamina") ? " with Stamina" : "");
    }
}
