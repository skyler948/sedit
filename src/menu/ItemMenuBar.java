package menu;

import javax.swing.*;
import java.util.ArrayList;

public class ItemMenuBar extends JMenuBar {

    private JMenu[] menus;

    private ArrayList<JMenuItem[]> items;

    public ItemMenuBar() {
        createMenuBar();
        createMenuBarAccelerator();
    }

    private void createMenuBar() {
        menus = new JMenu[]{
                new JMenu("File"),
                new JMenu("Edit"),
                new JMenu("View"),
                new JMenu("Settings"),
                new JMenu("Help")
        };

        items = new ArrayList<>();

        items.add(new JMenuItem[]{ // File
                new JMenuItem("New"),
                new JMenuItem("Open"),
                new JMenuItem("Save"),
                new JMenuItem("Save As"),
                new JMenuItem("Quit")
        });

        items.add(new JMenuItem[]{ // Edit
                new JMenuItem("Undo"),
                new JMenuItem("Redo"),
                new JMenuItem("Cut"),
                new JMenuItem("Copy"),
                new JMenuItem("Paste"),
                new JMenuItem("Find")
        });

        items.add(new JMenuItem[]{ // View
                new JMenuItem("Zoom In"),
                new JMenuItem("Zoom Out"),
                new JMenuItem("Reset Zoom")
        });

        items.add(new JMenuItem[]{ // Settings
                new JCheckBoxMenuItem("Show Status Panel", true),
                new JMenuItem("Configure Style")
        });

        items.add(new JMenuItem[]{ // Help
                new JMenuItem("Report Bug"),
                new JMenuItem("About sedit")
        });

        for (int i = 0; i < menus.length; i++) {
            for (JMenuItem item : items.get(i)) {
                menus[i].add(item);
            }

            add(menus[i]);
        }
    }

    private void createMenuBarAccelerator() {
        for (Item item : Item.values()) {
            if (item.key == null) continue;
            setItemAccelerator(item);
        }
    }

    private JMenuItem[] getItems(Item item) {
        return items.get(item.menu.id);
    }

    public JMenuItem getItem(Item item) {
        return getItems(item)[item.id];
    }

    private void setItemAccelerator(Item item) {
        getItem(item).setAccelerator(item.key);
    }

}
