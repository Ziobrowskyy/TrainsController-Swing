package com.ziobrowski.frames;

import com.ziobrowski.models.TrainOnStationModel;
import com.ziobrowski.tables.TrainOnStationTable;
import com.ziobrowski.trains.TrainStation;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TrainsOnStationFrame extends JFrame {
    final JScrollPane scrollPane;
    final JPanel mainPanel;
    final JPanel controllerPanel;
    final TrainOnStationTable trainOnStationTable;

    final int width = 800;
    final int height = 600;

    public TrainsOnStationFrame(TrainStation ts) {
        setSize(width, height);
        setTitle("Train on station " + ts.toString());
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        mainPanel = new JPanel();
        scrollPane = new JScrollPane();
        controllerPanel = new JPanel();
        add(mainPanel);
        add(scrollPane);
        add(controllerPanel);

        JLabel titleLabel = new JLabel("Trains on selected station");
        mainPanel.add(titleLabel);

        trainOnStationTable = new TrainOnStationTable(ts);
        scrollPane.setViewportView(trainOnStationTable);

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Remove");

        controllerPanel.add(addButton);
        controllerPanel.add(deleteButton);
        addButton.addActionListener(this::onAdd);
        deleteButton.addActionListener(this::onDelete);
        setVisible(true);
    }

    void onAdd(ActionEvent e) {
        new AddTrainOnStationFrame((TrainOnStationModel) trainOnStationTable.getModel());
    }

    void onDelete(ActionEvent e) {
        trainOnStationTable.remove();
    }
}
