package display;

import methods.CraftMethod;
import methods.CraftMethods;
import methods.RunecraftTask;
import org.rspeer.runetek.api.ClientSupplier;
import org.rspeer.ui.Log;
import task_structure.TreeScript;
import utils.AbyssLoadouts;
import utils.AbyssObstacles;
import utils.FoodTypes;
import utils.RuneTypes;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

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
    private JCheckBox useOfStaminaPotionsCheckBox;
    private JPanel staminaPanel;
    private JCheckBox useOfClanWarsCheckBox;
    private JComboBox<FoodTypes> foodChoice;
    private JCheckBox useOfStaminaPotionsAbyssCheckBox;
    private JPanel selfHealPanel;
    private JPanel taskFocus;
    private JCheckBox enableTaskQueuingCheckBox;
    private JPanel queuePanel;
    private JList<RunecraftTask> taskList;
    private JButton addQueuedTaskButton;
    private JPanel queueSpecifications;
    private JTextField levelDefinedInput;
    private JCheckBox untilInsufficientSuppliesCheckBox;
    private JLabel stopLevelLabel;
    private Class<?> craftClass;
    private boolean hasBeenSet;
    private final Queue<RunecraftTask> runecraftTasks;

    public RunecraftGUI() {
        super("DRunecraft Selection");
        runecraftTasks = new LinkedList<>();
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
        for (final FoodTypes foodType : FoodTypes.values())
            foodChoice.addItem(foodType);
        abyssPanel.setVisible(false);
        methodChoice.addActionListener(e -> {
            final boolean isAbyssVisible = Objects.requireNonNull(methodChoice.getSelectedItem()).equals(CraftMethods.ABYSS);
            staminaPanel.setVisible(!isAbyssVisible);
            abyssPanel.setVisible(isAbyssVisible);
            pack();
        });
        queueSpecifications.setVisible(false);
        queuePanel.setVisible(false);
        enableTaskQueuingCheckBox.addActionListener(e -> {
            final boolean isUsingQueuing = enableTaskQueuingCheckBox.isSelected();
            queueSpecifications.setVisible(isUsingQueuing);
            queuePanel.setVisible(isUsingQueuing);
            pack();
        });
        untilInsufficientSuppliesCheckBox.addActionListener(e -> {
            final boolean isUsingLevelDefinedStop = !untilInsufficientSuppliesCheckBox.isSelected();
            stopLevelLabel.setEnabled(isUsingLevelDefinedStop);
            levelDefinedInput.setEnabled(isUsingLevelDefinedStop);
        });
        addQueuedTaskButton.addActionListener(e -> {
            final String definedInput = getLevelDefinedInput();
            if (definedInput.equals("0") || !definedInput.matches("\\d{1,2}")) {
                Log.severe("Your level goal should be between 1 and 99!");
                return;
            }
            ((DefaultListModel<RunecraftTask>) taskList.getModel()).addElement(new RunecraftTask(this));
            pack();
        });
        useOfClanWarsCheckBox.addActionListener(e -> selfHealPanel.setVisible(!useOfClanWarsCheckBox.isSelected()));
        startButton.addActionListener(e -> {
            if (queuePanel.isVisible()) {
                final ListModel<RunecraftTask> listModel = taskList.getModel();
                final int modelSize = listModel.getSize();
                if (modelSize == 0) {
                    Log.severe("You haven't added any tasks - do so before starting!");
                    return;
                } else for (int i = 0; i < modelSize; i++) runecraftTasks.add(listModel.getElementAt(i));
            } else runecraftTasks.add(new RunecraftTask(this));
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

    public void exit() {
        setVisible(false);
    }

    public boolean isQueuePanelVisible() {
        return queuePanel.isVisible();
    }

    public boolean isUsingAbyss() {
        return abyssPanel.isVisible();
    }

    public boolean isUsingClanWars() {
        return useOfClanWarsCheckBox.isSelected();
    }

    public boolean isUsingSmallPouch() {
        return smallPouchCheckBox.isSelected();
    }

    public boolean isUsingMediumPouch() {
        return mediumPouchCheckBox.isSelected();
    }

    public boolean isUsingLargePouch() {
        return largePouchCheckBox.isSelected();
    }

    public boolean isUsingGiantPouch() {
        return giantPouchCheckBox.isSelected();
    }

    public Class<?> getCraftClass() {
        return ((CraftMethods) Objects.requireNonNull(methodChoice.getSelectedItem())).getMethod();
    }

    public String getMethodName() {
        return Objects.requireNonNull(methodChoice.getSelectedItem()).toString();
    }

    public boolean isUsingStamina() {
        final boolean abyssVisible = isUsingAbyss(), clanWarsSelected = isUsingClanWars();
        return (!abyssVisible && useOfStaminaPotionsCheckBox.isSelected()) || (abyssVisible && !clanWarsSelected && useOfStaminaPotionsAbyssCheckBox.isSelected());
    }

    public Set<String> getObstacles() {
        final Set<String> obstacles = new HashSet<>();
        for (final AbyssObstacles obstacle : ((AbyssLoadouts) Objects.requireNonNull(traversalChoice.getSelectedItem())).getObstacles())
            obstacles.add(obstacle.toString());
        return obstacles;
    }

    public RuneTypes getAbyssRuneSpecifier() {
        return (RuneTypes) abyssRuneSpecifier.getSelectedItem();
    }

    public FoodTypes getFoodChoice() {
        return (FoodTypes) foodChoice.getSelectedItem();
    }

    public boolean willRunAdNauseam() {
        return untilInsufficientSuppliesCheckBox.isSelected();
    }

    public String getLevelDefinedInput() {
        return levelDefinedInput.getText();
    }

    public Queue<RunecraftTask> getRunecraftTasks() {
        return runecraftTasks;
    }

    public CraftMethod getMethod(final TreeScript handler) {
        final boolean abyssVisible = abyssPanel.isVisible(), clanWarsSelected = useOfClanWarsCheckBox.isSelected();
        if ((!abyssVisible && useOfStaminaPotionsCheckBox.isSelected()) || (abyssVisible && !clanWarsSelected && useOfStaminaPotionsAbyssCheckBox.isSelected()))
            handler.addNotedSetting("stamina");
        if (abyssVisible) {
            if (smallPouchCheckBox.isSelected()) handler.addNotedSetting("Small pouch");
            if (mediumPouchCheckBox.isSelected()) handler.addNotedSetting("Medium pouch");
            if (largePouchCheckBox.isSelected()) handler.addNotedSetting("Large pouch");
            if (giantPouchCheckBox.isSelected()) handler.addNotedSetting("Giant pouch");
            for (final AbyssObstacles obstacle : ((AbyssLoadouts) traversalChoice.getSelectedItem()).getObstacles())
                handler.addNotedSetting(obstacle.toString());
        }
        try {
            return (CraftMethod) (abyssVisible ? clanWarsSelected ? craftClass.getDeclaredConstructor(TreeScript.class, RuneTypes.class).newInstance(handler, (RuneTypes) abyssRuneSpecifier.getSelectedItem()) : craftClass.getConstructor(TreeScript.class, RuneTypes.class, FoodTypes.class).newInstance(handler, (RuneTypes) abyssRuneSpecifier.getSelectedItem(), (FoodTypes) foodChoice.getSelectedItem()) : craftClass.getDeclaredConstructor(TreeScript.class).newInstance(handler));
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
