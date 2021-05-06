package com.ziobrowski.frames;

import com.ziobrowski.Main;
import com.ziobrowski.models.TrainOnStationModel;
import com.ziobrowski.trains.*;

import javax.swing.*;
import java.awt.*;

public class AddTrainOnStationFrame extends JFrame {
    final int width = 400;
    final int height = 300;
    final JPanel inputPanel;
    final JPanel buttonPanel;

    public AddTrainOnStationFrame(TrainOnStationModel model) {
        setSize(width, height);
        setTitle("Add train on station");
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

        JLabel trainLabel = new JLabel("Train");
        JLabel timeLabel = new JLabel("Time (hh:mm)");

        JComboBox<String> trainBox = new JComboBox<>((String[]) Main.controller.trains.stream().map(Train::toString).toArray());
        JTextField timeField = new JTextField("1:00");

        inputPanel.add(trainLabel);
        inputPanel.add(trainBox);
        inputPanel.add(timeLabel);
        inputPanel.add(timeField);

        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);

        addButton.addActionListener(e -> {
            Train train = Main.controller.findTrainByFullName((String) trainBox.getSelectedItem());
            Time time = new Time(timeField.getText());
            model.addRow(train, time);
        });

        setVisible(true);
    }
}
