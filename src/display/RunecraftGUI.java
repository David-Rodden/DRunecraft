package display;

import methods.CraftMethod;
import methods.CraftMethods;
import org.rspeer.runetek.api.ClientSupplier;
import task_structure.TreeScript;
import utils.AbyssLoadouts;
import utils.AbyssObstacles;
import utils.RuneTypes;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class RunecraftGUI extends JFrame {
    private JComboBox<CraftMethods> methodChoice;
    private JPanel selectionPanel;
    private JButton startButton;
    private JPanel abyssPanel;
    private JComboBox<RuneTypes> abyssRuneSpecifier;
    private JPanel pouchSelection;
    private JCheckBox smallPouchCheckBox;
    private JCheckBox mediumPouchCheckBox;
    private JCheckBox largePouchCheckBox;
    private JCheckBox giantPouchCheckBox;
    private JPanel traversalSelection;
    private JComboBox<AbyssLoadouts> traversalChoice;
    private Class<?> craftClass;
    private boolean hasBeenSet;

    public RunecraftGUI() {
        super("DRunecraft Selection");
        hasBeenSet = false;
        setContentPane(selectionPanel);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(ClientSupplier.get().getCanvas());
        for (final CraftMethods method : CraftMethods.values())
            methodChoice.addItem(method);
        for (final RuneTypes runeType : RuneTypes.values())
            abyssRuneSpecifier.addItem(runeType);
        for (final AbyssLoadouts loadout : AbyssLoadouts.values())
            traversalChoice.addItem(loadout);
        abyssPanel.setVisible(false);
        methodChoice.addActionListener(e -> {
            abyssPanel.setVisible(Objects.requireNonNull(methodChoice.getSelectedItem()).equals(CraftMethods.ABYSS));
            pack();
        });
        startButton.addActionListener(e -> {
            final CraftMethods selected = (CraftMethods) methodChoice.getSelectedItem();
            if (selected == null) return;
            craftClass = selected.getMethod();
            setVisible(false);
            hasBeenSet = true;
        });
        pack();
        setVisible(true);
    }

    public boolean isHidden() {
        return !isVisible();
    }

    public boolean hasBeenSet() {
        return hasBeenSet;
    }

    public CraftMethod getMethod(final TreeScript handler) {
        if (abyssPanel.isVisible()) {
            if (smallPouchCheckBox.isSelected()) handler.addNotedSetting("Small pouch");
            if (mediumPouchCheckBox.isSelected()) handler.addNotedSetting("Medium pouch");
            if (largePouchCheckBox.isSelected()) handler.addNotedSetting("Large pouch");
            if (giantPouchCheckBox.isSelected()) handler.addNotedSetting("Giant pouch");
            for (final AbyssObstacles obstacle : ((AbyssLoadouts) traversalChoice.getSelectedItem()).getObstacles())
                handler.addNotedSetting(obstacle.toString());
        }
        try {
            return (CraftMethod) (abyssPanel.isVisible() ? craftClass.getDeclaredConstructor(TreeScript.class, RuneTypes.class).newInstance(handler, (RuneTypes) abyssRuneSpecifier.getSelectedItem()) : craftClass.getDeclaredConstructor(TreeScript.class).newInstance(handler));
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
