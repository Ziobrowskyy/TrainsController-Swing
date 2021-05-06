package com.ziobrowski.frames;

import com.ziobrowski.models.TrainModel;
import com.ziobrowski.tables.TrainTable;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TrainsFrame extends JFrame {
    final JScrollPane scrollPane;
    final JPanel mainPanel;
    final JPanel controllerPanel;
    final TrainTable trainTable;

    final int width = 800;
    final int height = 600;

    public TrainsFrame() {
        setSize(width, height);
        setTitle("Train control");
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        mainPanel = new JPanel();
        scrollPane = new JScrollPane();
        controllerPanel = new JPanel();
        add(mainPanel);
        add(scrollPane);
        add(controllerPanel);

        JLabel titleLabel = new JLabel("Add/remove trains");
        mainPanel.add(titleLabel);

        trainTable = new TrainTable();
        scrollPane.setViewportView(trainTable);

        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Remove");

        controllerPanel.add(addButton);
        controllerPanel.add(editButton);
        controllerPanel.add(deleteButton);
        addButton.addActionListener(this::onAdd);
        editButton.addActionListener(this::onEdit);
        deleteButton.addActionListener(this::onDelete);
        setVisible(true);
    }

    void onAdd(ActionEvent e) {
        new AddTrainFrame((TrainModel) trainTable.getModel());
    }

    void onEdit(ActionEvent e) {
        trainTable.edit();
    }

    void onDelete(ActionEvent e) {
        trainTable.remove();
    }
}
