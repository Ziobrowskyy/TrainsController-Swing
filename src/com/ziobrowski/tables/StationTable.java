package com.ziobrowski.tables;

import com.ziobrowski.models.StationModel;

import javax.swing.*;

public class StationTable extends JTable {
    final StationModel model;

    final String[] headers = {"ID", "Name", "Maximum capacity", "Current load"};

    //
    public StationTable() {
        model = new StationModel(0, headers);
        setModel(model);
        setAutoCreateRowSorter(true);
    }

    public void edit() {
        int row = getSelectedRow();
        int col = getSelectedColumn();
        if(col == 0)
            return;
        Object x = JOptionPane.showInputDialog(
                null, "Edit " + headers[col], "Edit station", JOptionPane.QUESTION_MESSAGE,
                null, null, getValueAt(row, col));
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
