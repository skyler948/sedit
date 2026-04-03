package display;

import launcher.Launcher;

import javax.swing.*;
import java.awt.*;

public class AboutDisplay extends Display {

    private JLabel titleLabel;
    private JPanel titlePanel;

    private JLabel infoLabel;
    private JPanel infoPanel;

    private JLabel versionLabel;
    private JPanel versionPanel;

    public AboutDisplay(int width, int height) {
        super(width, height);
        title = "About";
    }

    @Override
    public void createDisplayElements() {
        panel = new JPanel(new BorderLayout(0, 20));

        titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panel.add(titlePanel, BorderLayout.NORTH);

        titleLabel = new JLabel("-- About --");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        titlePanel.add(titleLabel);

        infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panel.add(infoPanel, BorderLayout.CENTER);

        infoLabel = new JLabel("sedit is a simple text editor written in Java.");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        infoPanel.add(infoLabel);

        versionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panel.add(versionPanel, BorderLayout.SOUTH);

        versionLabel = new JLabel("Version: " + Launcher.VERSION_STRING);
        versionLabel.setFont(new Font("Arial", Font.ITALIC, 10));

        versionPanel.add(versionLabel);

        frame.add(panel);
    }

}
