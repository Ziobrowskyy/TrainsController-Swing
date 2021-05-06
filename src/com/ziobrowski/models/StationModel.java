package com.ziobrowski.models;

import com.ziobrowski.Main;
import com.ziobrowski.trains.*;

import javax.swing.table.DefaultTableModel;

public class StationModel extends DefaultTableModel {
    public StationModel(int rowCount, Object[] headers) {
        super(rowCount, headers.length);
        setColumnIdentifiers(headers);
        initializeData();
    }

    void initializeData() {
        Main.controller.trainStations.forEach(ts -> addRow(ts.toObjectArray()));
    }

    public void addRow(TrainStation ts) {
        Main.controller.addStation(ts);
        addRow(ts.toObjectArray());
    }

    @Override
    public void removeRow(int row) {
        int id = (int) getValueAt(row, 0);
        String name = (String) getValueAt(row, 1);
        super.removeRow(row);
        Main.controller.removeStationByIdAndName(id, name);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > Main.controller.trainStations.size())
            return null;
        TrainStation ts = Main.controller.trainStations.get(rowIndex);
        switch (columnIndex) {
            case (0) -> {
                return ts.getId();
            }
            case (1) -> {
                return ts.name;
            }
            case (2) -> {
                return ts.maximalCapacity;
            }
            case (3) -> {
                return ts.getCurrentLoad();
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        TrainStation ts = Main.controller.trainStations.get(rowIndex);
        switch (columnIndex) {
            case (1) -> ts.name = (String) aValue;
            case (2) -> ts.maximalCapacity = Integer.parseInt((String) aValue);
        }
    }

}
