package com.ziobrowski.frames;

import com.ziobrowski.Main;
import com.ziobrowski.models.TrainModel;
import com.ziobrowski.trains.*;

import javax.swing.*;
import java.awt.*;

public class AddTrainFrame extends JFrame {
    final int width = 400;
    final int height = 300;
    final JPanel inputPanel;
    final JPanel buttonPanel;

    public AddTrainFrame(TrainModel model) {
        setSize(width, height);
        setTitle("Add new train");
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        add(inputPanel);
        add(buttonPanel);

        JLabel nameLabel = new JLabel("Name");
        JLabel typeLabel = new JLabel("Type");
        JLabel stateLabel = new JLabel("State");
        JLabel capacityLabel = new JLabel("Capacity");
        JLabel trainStationLabel = new JLabel("Train station");
        JLabel travelTimeLabel = new JLabel("Travel time (hour:minute)");

        JTextField nameField = new JTextField("Pociung");
        JComboBox<TrainType> typeBox = new JComboBox<>(TrainType.values());
        JTextField capacityField = new JTextField("100");
        JComboBox<TrainState> stateBox = new JComboBox<>(TrainState.values());
        JTextField travelTimeField = new JTextField("1:00");
        Object[] tsArray = Main.controller.trainStations.stream().map(TrainStation::toString).toArray();
        Object[] tsArrayWithNull = new Object[tsArray.length+1];
        System.arraycopy(tsArray, 0, tsArrayWithNull, 1, tsArray.length);
        tsArrayWithNull[0] = null;
        JComboBox<Object> trainStationBox = new JComboBox<>(tsArrayWithNull);

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(typeLabel);
        inputPanel.add(typeBox);
        inputPanel.add(stateLabel);
        inputPanel.add(stateBox);
        inputPanel.add(capacityLabel);
        inputPanel.add(capacityField);
        inputPanel.add(trainStationLabel);
        inputPanel.add(trainStationBox);
        inputPanel.add(travelTimeLabel);
        inputPanel.add(travelTimeField);

        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);

        addButton.addActionListener(e -> {
            Train t;
            TrainState state = (TrainState) stateBox.getSelectedItem();
            String name = nameField.getText();
            int capacity = Integer.parseInt(capacityField.getText());
            TrainStation ts = Main.controller.getStationByFullName((String)trainStationBox.getSelectedItem());
            Time travelTime = new Time(travelTimeField.getText());
            if (typeBox.getSelectedItem() == TrainType.CARGO) {
                //(TrainState state, String name, int capacity, TrainStation current, Time travelTime) {
                t = new CargoTrain(state, name, capacity, ts, travelTime);
            } else {
                t = new PassengerTrain(state, name, capacity, ts, travelTime);
            }
            model.addRow(t);
        });

        setVisible(true);
    }
}
