package display;

import methods.CraftMethod;
import methods.CraftMethods;
import task_structure.TreeScript;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class RunecraftGUI extends Frame {
    private JComboBox<CraftMethods> methodChoice;
    private JPanel selectionPanel;
    private JButton startButton;
    private Class<?> craftMethod;
    private boolean hasBeenSet;
    private int selectedId;

    public RunecraftGUI() {
        hasBeenSet = false;
        final JFrame frame = new JFrame("Runecraft Selection");
        frame.setContentPane(selectionPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        for (final CraftMethods method : CraftMethods.values())
            methodChoice.addItem(method);
        startButton.addActionListener(e -> {
            final CraftMethods selected = (CraftMethods) methodChoice.getSelectedItem();
            if (selected == null) return;
            craftMethod = selected.getMethod();
            selectedId = selected.getId();
            frame.setVisible(false);
            hasBeenSet = true;
        });
        frame.setVisible(true);
    }

    public boolean hasBeenSet() {
        return hasBeenSet;
    }

    public CraftMethod getMethod(final TreeScript handler) {
        final Constructor<?> methodConstructor;
        try {
            methodConstructor = craftMethod.getDeclaredConstructor(TreeScript.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
        try {
            return (CraftMethod) methodConstructor.newInstance(handler);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
