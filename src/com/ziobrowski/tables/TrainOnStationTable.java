package com.ziobrowski.tables;

import com.ziobrowski.models.TrainOnStationModel;
import com.ziobrowski.trains.TrainStation;

import javax.swing.*;

public class TrainOnStationTable extends JTable {
    final TrainOnStationModel model;
    final TrainStation trainStation;
    final String[] headers = {"ID", "Name", "Type", "State", "Capacity", "Travel time"};

    //
    public TrainOnStationTable(TrainStation ts) {
        trainStation = ts;
        model = new TrainOnStationModel(0, headers, ts);
        setModel(model);
        setAutoCreateRowSorter(true);

    }

    public void remove() {
        int row = getSelectedRow();
        model.removeRow(row);
        model.fireTableDataChanged();
    }

}
