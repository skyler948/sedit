package status;

import display.Display;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class StatusPanel extends JPanel {

    private JLabel currentFileStatus;
    private JLabel fontSizeStatus;

    private Display display;

    public StatusPanel(Display display) {
        this.display = display;
        createStatusBar();
    }

    private void createStatusBar() {
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        currentFileStatus = new JLabel();
        currentFileStatus.setHorizontalAlignment(SwingConstants.LEFT);
        setDefaultStatus();

        fontSizeStatus = new JLabel();
        setFontSizeStatus();

        add(currentFileStatus);
        add(fontSizeStatus);
    }

    public void setCurrentFileStatus(String currentFileStatus) {
        this.currentFileStatus.setText(" " + currentFileStatus);
    }

    public void setFontSizeStatus() {
        fontSizeStatus.setText(" | " + display.getTextSize());
    }

    public void setDefaultStatus() {
        this.currentFileStatus.setText(" < unnamed >");
    }

}
