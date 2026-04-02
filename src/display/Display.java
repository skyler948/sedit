package display;

import menu.ItemMenuBar;
import menu.MenuBarHandler;
import status.StatusPanel;

import javax.swing.*;
import java.awt.*;

public class Display {

    private int width, height;
    private Dimension dimension;

    private JFrame frame;

    private ItemMenuBar menuBar;

    private JTextArea textArea;
    private JScrollPane scrollPane;

    private StatusPanel statusBar;

    private MenuBarHandler menuBarHandler;

    public Display(int width, int height) {
        setDimensions(width, height);

        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame("sedit");

        frame.setSize(dimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        createDisplayElements();

        frame.setVisible(true);
    }

    private void createDisplayElements() {
        menuBar = new ItemMenuBar();

        frame.setJMenuBar(menuBar);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setTabSize(1);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(scrollPane);

        statusBar = new StatusPanel();

        frame.add(statusBar, BorderLayout.SOUTH);

        menuBarHandler = new MenuBarHandler(menuBar, textArea, statusBar);
    }

    public void setDimensions(int width, int height) {
        this.width = Math.max(width, 200);
        this.height = Math.max(height, 100);
        this.dimension = new Dimension(this.width, this.height);

        if (frame != null) {
            frame.setSize(dimension);
        }
    }

}
