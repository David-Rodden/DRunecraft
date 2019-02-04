package display;

import methods.CraftMethod;
import methods.CraftMethods;
import org.rspeer.runetek.api.ClientSupplier;
import org.rspeer.runetek.providers.subclass.GameCanvas;
import task_structure.TreeScript;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class RunecraftGUI extends JFrame {
    private JComboBox<CraftMethods> methodChoice;
    private JPanel selectionPanel;
    private JButton startButton;
    private Class<?> craftClass;
    private boolean hasBeenSet;

    public static void main(String[] args) {
        new RunecraftGUI();
    }

    public RunecraftGUI() {
        super("DRunecraft Selection");
        hasBeenSet = false;
        setContentPane(selectionPanel);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(ClientSupplier.get().getCanvas());
        for (final CraftMethods method : CraftMethods.values())
            methodChoice.addItem(method);
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
        final Constructor<?> craftConstructor;
        try {
            craftConstructor = craftClass.getDeclaredConstructor(TreeScript.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
        try {
            return (CraftMethod) craftConstructor.newInstance(handler);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
