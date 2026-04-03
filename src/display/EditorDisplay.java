package display;

import menu.ItemMenuBar;
import menu.MenuBarHandler;
import status.StatusPanel;

import javax.swing.*;
import java.awt.*;

public class EditorDisplay extends Display {

    private ItemMenuBar menuBar;

    private JTextArea textArea;
    private JScrollPane scrollPane;

    private StatusPanel statusBar;

    private MenuBarHandler menuBarHandler;

    public EditorDisplay(int width, int height) {
        super(width, height);
        title = "sedit";
        closeOperation = JFrame.EXIT_ON_CLOSE;
    }

    @Override
    public void createDisplayElements() {
        menuBar = new ItemMenuBar();

        frame.setJMenuBar(menuBar);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setTabSize(1);
        textArea.setFont(textFont);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(scrollPane);

        statusBar = new StatusPanel(this);

        frame.add(statusBar, BorderLayout.SOUTH);

        menuBarHandler = new MenuBarHandler(this);
    }

    @Override
    public void changeFontSize(int amount) {
        int newSize = Math.max(2, textFont.getSize() + amount);
        textFont = new Font(DEFAULT_NAME, Font.PLAIN, newSize);
        textArea.setFont(textFont);
        statusBar.setFontSizeStatus();
    }

    @Override
    public void resetFontSize() {
        textFont = new Font(DEFAULT_NAME, Font.PLAIN, DEFAULT_SIZE);
        textArea.setFont(textFont);
        statusBar.setFontSizeStatus();
    }

    public ItemMenuBar getMenuBar() {
        return menuBar;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public StatusPanel getStatusBar() {
        return statusBar;
    }

    public MenuBarHandler getMenuBarHandler() {
        return menuBarHandler;
    }

}
