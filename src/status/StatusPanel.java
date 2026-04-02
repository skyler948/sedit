package status;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class StatusPanel extends JPanel {

    private JLabel status;

    public StatusPanel() {
        createStatusBar();
    }

    private void createStatusBar() {
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        status = new JLabel();
        status.setHorizontalAlignment(SwingConstants.LEFT);
        setDefaultStatus();

        add(status);
    }

    public void setStatus(String status) {
        this.status.setText(" " + status);
    }

    public void setDefaultStatus() {
        this.status.setText(" < unnamed >");
    }

}
