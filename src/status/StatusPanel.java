package status;

import display.EditorDisplay;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.text.BadLocationException;

public class StatusPanel extends JPanel {

    private JLabel currentFileStatus;
    private JLabel fontSizeStatus;
    private JLabel caretPositionStatus;

    private EditorDisplay editorDisplay;

    public StatusPanel(EditorDisplay editorDisplay) {
        this.editorDisplay = editorDisplay;
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

        caretPositionStatus = new JLabel();
        setCaretPositionStatus();

        add(currentFileStatus);
        add(fontSizeStatus);
        add(caretPositionStatus);
    }

    public void setCaretPositionStatus() {
        try {
            int row = editorDisplay.getTextArea().getLineOfOffset(editorDisplay.getTextArea().getCaretPosition());
            int column = editorDisplay.getTextArea().getCaretPosition() - editorDisplay.getTextArea().getLineStartOffset(row);

            caretPositionStatus.setText(" | " + row + ":" + column);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCurrentFileStatus(String currentFileStatus) {
        this.currentFileStatus.setText(" " + currentFileStatus);
    }

    public void setFontSizeStatus() {
        fontSizeStatus.setText(" | " + editorDisplay.getTextSize());
    }

    public void setDefaultStatus() {
        this.currentFileStatus.setText(" < unnamed >");
    }

}
