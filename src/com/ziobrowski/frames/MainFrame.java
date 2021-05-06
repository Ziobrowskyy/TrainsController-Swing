package com.ziobrowski.frames;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    final int width = 800;
    final int height = 600;
    final JPanel mainPanel;

    public MainFrame() {
        setSize(width, height);
        setTitle("Welcome to train station control app!");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        add(mainPanel);

        GridBagConstraints c = new GridBagConstraints();

        JLabel titleLabel = new JLabel("SELECT ACTION!");
        titleLabel.setFont(new Font("Sans-serif", Font.PLAIN, 48));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,0,0,0);
        c.gridwidth = 2;
        c.weightx = 1;
        c.weighty = 0.8;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(titleLabel, c);

        JButton trainsButton = new JButton("Edit trains");
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridy = 1;
        c.gridx = 0;
        mainPanel.add(trainsButton, c);
        JButton stationsButton = new JButton("Edit stations");
        c.gridx = 1;
        mainPanel.add(stationsButton, c);

        trainsButton.addActionListener(e -> new TrainsFrame());
        stationsButton.addActionListener(e -> new StationsFrame());
        setVisible(true);
    }
}
