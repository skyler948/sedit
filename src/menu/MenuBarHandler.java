package menu;

import status.StatusPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MenuBarHandler {

    private ItemMenuBar menuBar;

    private JTextArea textArea;

    private StatusPanel statusBar;

    private File currentFile;

    public MenuBarHandler(ItemMenuBar menuBar, JTextArea textArea, StatusPanel statusBar) {
        this.menuBar = menuBar;
        this.textArea = textArea;
        this.statusBar = statusBar;

        setMenuActions();
    }

    private void setMenuActions() {
        menuBar.getItem(Menu.FILE, Item.NEW).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                currentFile = null;
                statusBar.setDefaultStatus();
            }
        });

        menuBar.getItem(Menu.FILE, Item.OPEN).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser open = new JFileChooser();

                if (open.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
                    currentFile = open.getSelectedFile();

                    if (!currentFile.isFile()) return;

                    statusBar.setStatus(currentFile.getName());

                    openFile();
                }
            }
        });

        menuBar.getItem(Menu.FILE, Item.SAVE).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser save;
                if (currentFile == null) {
                    save = new JFileChooser();

                    if (save.showSaveDialog(save) == JFileChooser.APPROVE_OPTION) {
                        currentFile = save.getSelectedFile();
                        statusBar.setStatus(currentFile.getName());
                    }
                }

                saveFile();
            }
        });

        menuBar.getItem(Menu.FILE, Item.SAVE_AS).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser save = new JFileChooser();

                if (save.showSaveDialog(save) == JFileChooser.APPROVE_OPTION) {
                    currentFile = save.getSelectedFile();
                    statusBar.setStatus(currentFile.getName());

                    saveFile();
                }
            }
        });

        menuBar.getItem(Menu.FILE, Item.QUIT).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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

            textArea.setText(string.toString());

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveFile() {
        try {
            FileWriter writer = new FileWriter(currentFile);

            writer.write(textArea.getText());

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
