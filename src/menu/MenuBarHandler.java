package menu;

import display.AboutDisplay;
import display.ConfigDisplay;
import display.EditorDisplay;
import display.FindDisplay;
import net.Link;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MenuBarHandler {

    private EditorDisplay editorDisplay;

    private AboutDisplay aboutDisplay;
    private FindDisplay findDisplay;
    private ConfigDisplay configDisplay;

    private File currentFile;

    private static final byte ZOOM = 2;

    public MenuBarHandler(EditorDisplay editorDisplay) {
        this.editorDisplay = editorDisplay;
        aboutDisplay = new AboutDisplay(380, 175);
        findDisplay = new FindDisplay(480, 360);
        configDisplay = new ConfigDisplay(900, 500);

        setMenuActions();
    }

    private void setMenuActions() {
        editorDisplay.getMenuBar().getItem(Item.NEW).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorDisplay.getTextArea().setText("");
                currentFile = null;
                editorDisplay.getStatusBar().setDefaultStatus();
            }
        });

        editorDisplay.getMenuBar().getItem(Item.OPEN).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser open = new JFileChooser();

                if (open.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
                    currentFile = open.getSelectedFile();

                    if (!currentFile.isFile()) return;

                    editorDisplay.getStatusBar().setCurrentFileStatus(currentFile.getName());

                    openFile();
                }
            }
        });

        editorDisplay.getMenuBar().getItem(Item.SAVE).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser save;
                if (currentFile == null) {
                    save = new JFileChooser();

                    if (save.showSaveDialog(save) == JFileChooser.APPROVE_OPTION) {
                        currentFile = save.getSelectedFile();
                        editorDisplay.getStatusBar().setCurrentFileStatus(currentFile.getName());
                    }
                }

                saveFile();
            }
        });

        editorDisplay.getMenuBar().getItem(Item.SAVE_AS).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser save = new JFileChooser();

                if (save.showSaveDialog(save) == JFileChooser.APPROVE_OPTION) {
                    currentFile = save.getSelectedFile();
                    editorDisplay.getStatusBar().setCurrentFileStatus(currentFile.getName());

                    saveFile();
                }
            }
        });

        editorDisplay.getMenuBar().getItem(Item.QUIT).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        editorDisplay.getMenuBar().getItem(Item.UNDO).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Undo/redo logic
            }
        });

        editorDisplay.getMenuBar().getItem(Item.REDO).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Undo/redo logic
            }
        });

        editorDisplay.getMenuBar().getItem(Item.CUT).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorDisplay.getTextArea().cut();
            }
        });

        editorDisplay.getMenuBar().getItem(Item.COPY).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorDisplay.getTextArea().copy();
            }
        });

        editorDisplay.getMenuBar().getItem(Item.PASTE).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorDisplay.getTextArea().paste();
            }
        });

        editorDisplay.getMenuBar().getItem(Item.FIND).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (findDisplay.isDisplayOpen()) return;
                findDisplay.createDisplay();
            }
        });

        editorDisplay.getMenuBar().getItem(Item.ZOOM_IN).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorDisplay.changeFontSize(ZOOM);
            }
        });

        editorDisplay.getMenuBar().getItem(Item.ZOOM_OUT).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorDisplay.changeFontSize(-ZOOM);
            }
        });

        editorDisplay.getMenuBar().getItem(Item.RESET_ZOOM).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorDisplay.resetFontSize();
            }
        });

        editorDisplay.getMenuBar().getItem(Item.SHOW_STATUS_PANEL).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem box = (JCheckBoxMenuItem) editorDisplay.getMenuBar().getItem(Item.SHOW_STATUS_PANEL);
                editorDisplay.getStatusBar().setVisible(box.getState());
            }
        });

        editorDisplay.getMenuBar().getItem(Item.CONFIGURE_STYLE).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (configDisplay.isDisplayOpen()) return;
                configDisplay.createDisplay();
            }
        });

        editorDisplay.getMenuBar().getItem(Item.REPORT_BUG).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Link.openLink("https://github.com/skyler948/sedit/issues");
            }
        });

        editorDisplay.getMenuBar().getItem(Item.ABOUT_SEDIT).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (aboutDisplay.isDisplayOpen()) return;
                aboutDisplay.createDisplay();
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

            editorDisplay.getTextArea().setText(string.toString());

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveFile() {
        try {
            FileWriter writer = new FileWriter(currentFile);

            writer.write(editorDisplay.getTextArea().getText());

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
