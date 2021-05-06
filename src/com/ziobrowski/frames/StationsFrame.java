package com.ziobrowski.frames;

import com.ziobrowski.Main;
import com.ziobrowski.models.StationModel;
import com.ziobrowski.tables.StationTable;
import com.ziobrowski.trains.TrainStation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StationsFrame extends JFrame {
    final int width = 800;
    final int height = 600;
    final JPanel mainPanel;
    final JScrollPane scrollPane;
    final JPanel controllerPanel;
    final StationTable stationTable;

    public StationsFrame() {
        setSize(width, height);
        setTitle("Train station control");
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

        JLabel titleLabel = new JLabel("Manage stations");
        mainPanel.add(titleLabel);

        stationTable = new StationTable();
        scrollPane.setViewportView(stationTable);

        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton showButton = new JButton("Show departures");
        JButton deleteButton = new JButton("Remove");

        controllerPanel.add(addButton);
        controllerPanel.add(editButton);
        controllerPanel.add(showButton);
        controllerPanel.add(deleteButton);
        addButton.addActionListener(this::onAdd);
        editButton.addActionListener(this::onEdit);
        showButton.addActionListener(this::onShow);
        deleteButton.addActionListener(this::onDelete);
        setVisible(true);
    }

    void onAdd(ActionEvent e) {
        new AddStationFrame((StationModel) stationTable.getModel());
    }

    void onEdit(ActionEvent e) {
        stationTable.edit();
    }

    void onShow(ActionEvent e) {
        int row = stationTable.getSelectedRow();
        if (row < 0) {
            return;
        }
        int id = (int) stationTable.getValueAt(row, 0);
        String name = (String) stationTable.getValueAt(row, 1);
        TrainStation ts = Main.controller.getStationByIdAndName(id, name);
        JFrame frame = new TrainsOnStationFrame(ts);
        frame.addWindowListener(new WindowAdapter() {
                                    @Override
                                    public void windowClosing(WindowEvent e) {
                                        ((DefaultTableModel) stationTable.getModel()).fireTableDataChanged();
                                    }
                                }
        );

    }

    void onDelete(ActionEvent e) {
        stationTable.remove();
    }
}
