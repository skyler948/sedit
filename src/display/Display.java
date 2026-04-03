package display;

import javax.swing.*;
import java.awt.*;

public abstract class Display {

    protected static final String DEFAULT_NAME = "Arial";
    protected static final byte DEFAULT_SIZE = 12;

    protected Font textFont;

    protected int width, height;
    protected Dimension dimension;

    protected String title = "Default Title";
    protected int closeOperation;

    protected JFrame frame;
    protected JPanel panel;

    public Display(int width, int height) {
        setDimensions(width, height);

        textFont = new Font(DEFAULT_NAME, Font.PLAIN, DEFAULT_SIZE);
        closeOperation = JFrame.DISPOSE_ON_CLOSE;
    }

    public void createDisplay() {
        frame = new JFrame(title);

        frame.setSize(dimension);
        frame.setDefaultCloseOperation(closeOperation);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        createDisplayElements();

        frame.setVisible(true);
    }

    public abstract void createDisplayElements();

    public void setDimensions(int width, int height) {
        this.width = Math.max(width, 200);
        this.height = Math.max(height, 100);
        this.dimension = new Dimension(this.width, this.height);

        if (frame != null) {
            frame.setSize(dimension);
        }
    }

    public void changeFontSize(int amount) {
        int newSize = Math.max(2, textFont.getSize() + amount);
        textFont = new Font(DEFAULT_NAME, Font.PLAIN, newSize);
    }

    public void resetFontSize() {
        textFont = new Font(DEFAULT_NAME, Font.PLAIN, DEFAULT_SIZE);
    }

    public int getTextSize() {
        return textFont.getSize();
    }

    public Font getTextFont() {
        return textFont;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public boolean isDisplayOpen() {
        if (frame == null) return false;
        return frame.isShowing();
    }

}
