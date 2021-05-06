package com.ziobrowski.frames;

import com.ziobrowski.models.StationModel;
import com.ziobrowski.trains.*;

import javax.swing.*;
import java.awt.*;

public class AddStationFrame extends JFrame {
    final int width = 400;
    final int height = 300;
    final JPanel inputPanel;
    final JPanel buttonPanel;

    public AddStationFrame(StationModel model) {
        setSize(width, height);
        setTitle("Add new train");
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        add(inputPanel);
        add(buttonPanel);

        JLabel nameLabel = new JLabel("Name");
        JLabel maxCapacityLabel = new JLabel("Max capacity");

        JTextField nameField = new JTextField("Stacyjka");
        JTextField maxCapacityField = new JTextField("10");

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(maxCapacityLabel);
        inputPanel.add(maxCapacityField);

        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            int maxCapacity = Integer.parseInt(maxCapacityField.getText());
            TrainStation ts = new TrainStation(name, maxCapacity);
            model.addRow(ts);
        });

        setVisible(true);
    }
}
