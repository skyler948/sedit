package menu;

import display.Display;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MenuBarHandler {

    private Display display;

    private File currentFile;

    private static final byte ZOOM = 2;

    public MenuBarHandler(Display display) {
        this.display = display;

        setMenuActions();
    }

    private void setMenuActions() {
        display.getMenuBar().getItem(Item.NEW).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.getTextArea().setText("");
                currentFile = null;
                display.getStatusBar().setDefaultStatus();
            }
        });

        display.getMenuBar().getItem(Item.OPEN).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser open = new JFileChooser();

                if (open.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
                    currentFile = open.getSelectedFile();

                    if (!currentFile.isFile()) return;

                    display.getStatusBar().setCurrentFileStatus(currentFile.getName());

                    openFile();
                }
            }
        });

        display.getMenuBar().getItem(Item.SAVE).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser save;
                if (currentFile == null) {
                    save = new JFileChooser();

                    if (save.showSaveDialog(save) == JFileChooser.APPROVE_OPTION) {
                        currentFile = save.getSelectedFile();
                        display.getStatusBar().setCurrentFileStatus(currentFile.getName());
                    }
                }

                saveFile();
            }
        });

        display.getMenuBar().getItem(Item.SAVE_AS).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser save = new JFileChooser();

                if (save.showSaveDialog(save) == JFileChooser.APPROVE_OPTION) {
                    currentFile = save.getSelectedFile();
                    display.getStatusBar().setCurrentFileStatus(currentFile.getName());

                    saveFile();
                }
            }
        });

        display.getMenuBar().getItem(Item.QUIT).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        display.getMenuBar().getItem(Item.ZOOM_IN).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.changeFontSize(ZOOM);
            }
        });

        display.getMenuBar().getItem(Item.ZOOM_OUT).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.changeFontSize(-ZOOM);
            }
        });
    }

    private void openFile() {
        try {
            Scanner scanner = new Scanner(currentFile);

            StringBuilder string = new StringBuilder();

            while (scanner.hasNextLine()) {
                string.append(scanner.nextLine());
                string.append("\n");
            }

            display.getTextArea().setText(string.toString());

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveFile() {
        try {
            FileWriter writer = new FileWriter(currentFile);

            writer.write(display.getTextArea().getText());

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
