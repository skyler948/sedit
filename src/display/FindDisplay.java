package display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class FindDisplay extends Display {

    private EditorDisplay display;

    private JPanel inputPanel;
    private JLabel inputLabel;
    private JTextField inputField;
    private JButton inputButton;

    private JPanel caseSensitivePanel;
    private JCheckBox caseSensitiveButton;

    private JPanel infoPanel;
    private JLabel infoLabel;

    private int recentIndex;
    private int globalIndex;

    public FindDisplay(int width, int height, EditorDisplay display) {
        super(width, height);
        this.display = display;
        title = "Find";
    }

    @Override
    public void createDisplayElements() {
        globalIndex = 0;
        recentIndex = 0;

        panel = new JPanel(new BorderLayout(20, 20));

        inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));

        inputLabel = new JLabel("Input Text:");
        inputField = new JTextField(20);
        inputButton = new JButton("Find!");

        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(inputButton);
        frame.getRootPane().setDefaultButton(inputButton);

        panel.add(inputPanel, BorderLayout.NORTH);

        caseSensitivePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        caseSensitiveButton = new JCheckBox("Case Sensitive?", true);

        caseSensitivePanel.add(caseSensitiveButton);

        panel.add(caseSensitivePanel, BorderLayout.EAST);

        infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));

        infoLabel = new JLabel("Found text at index: ");

        infoPanel.add(infoLabel);

        panel.add(infoPanel, BorderLayout.WEST);

        frame.add(panel);

        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (caseSensitiveButton.isSelected()) {
                    recentIndex = findSubstringIndex(display.getTextArea().getText(), inputField.getText(), globalIndex);
                } else {
                    recentIndex = findSubstringIndex(display.getTextArea().getText().toLowerCase(Locale.ROOT),
                            inputField.getText().toLowerCase(Locale.ROOT), globalIndex);
                }

                if (recentIndex != -1) {
                    globalIndex = recentIndex + inputField.getText().length();

                    display.getTextArea().setSelectionStart(recentIndex);
                    display.getTextArea().setSelectionEnd(globalIndex);
                } else {
                    globalIndex = 0;

                    display.getTextArea().setSelectionStart(0);
                    display.getTextArea().setSelectionEnd(0);
                }
                infoLabel.setText("Found text at index: " + (recentIndex == -1 ? "None" : recentIndex));
            }
        });

        caseSensitiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetSearchIndex();
            }
        });
    }

    private int findSubstringIndex(String docstring, String substring, int startingIndex) {
        int docLength = docstring.length();
        int substringLength = substring.length();

        for (int i = startingIndex; i <= docLength - substringLength; i++) {
            int j;
            for (j = 0; j < substringLength; j++) {
                if (docstring.charAt(i + j) != substring.charAt(j)) {
                    break;
                }
            }

            if (j == substringLength) return i;
        }

        return -1;
    }

    private void resetSearchIndex() {
        globalIndex = 0;
        recentIndex = 0;
        infoLabel.setText("Found text at index: ");
        display.getTextArea().setSelectionStart(0);
        display.getTextArea().setSelectionEnd(0);
    }

}
