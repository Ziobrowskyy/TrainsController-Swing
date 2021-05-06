package com.ziobrowski.tables;

import com.ziobrowski.Main;
import com.ziobrowski.models.TrainModel;
import com.ziobrowski.trains.TrainState;
import com.ziobrowski.trains.TrainStation;
import com.ziobrowski.trains.TrainType;

import javax.swing.*;

public class TrainTable extends JTable {
    final TrainModel model;

    final String[] headers = {"ID", "Name", "Type", "State", "Capacity", "Train station", "Travel time"};

    //
    public TrainTable() {
        model = new TrainModel(0, headers);
        setModel(model);
        setAutoCreateRowSorter(true);
//        TableRowSorter<TableModel> sorter = new TableRowSorter<>(getModel());
    }

    public void edit() {
        int row = getSelectedRow();
        int col = getSelectedColumn();
        if(col == 0 || col < 0) {
            return;
        }
        Object x;
        switch (col) {
            case (2) -> {
                JComboBox<TrainType> options = new JComboBox<>(TrainType.values());
                JOptionPane.showMessageDialog(null, options, "Select train type", JOptionPane.QUESTION_MESSAGE);
                x = options.getSelectedItem();
            }
            case (3) -> {
                JComboBox<TrainState> options = new JComboBox<>(TrainState.values());
                JOptionPane.showMessageDialog(null, options, "Select train state", JOptionPane.QUESTION_MESSAGE);
                x = options.getSelectedItem();
            }
            case (5) -> {
                JComboBox<String> options = new JComboBox<>((String[])Main.controller.trainStations.stream().map(TrainStation::toString).toArray());
                JOptionPane.showMessageDialog(null, options, "Select station", JOptionPane.QUESTION_MESSAGE);
                x = options.getSelectedItem();
            }
            default -> x = JOptionPane.showInputDialog(
                    null, "Edit " + headers[col], "Edit train", JOptionPane.QUESTION_MESSAGE,
                    null, null, getValueAt(row, col));
        }
        if (x == null)
            return;
        model.setValueAt(x, row, col);
        model.fireTableDataChanged();
    }

    public void remove() {
        int row = getSelectedRow();
        model.removeRow(row);
        model.fireTableDataChanged();
    }

}
