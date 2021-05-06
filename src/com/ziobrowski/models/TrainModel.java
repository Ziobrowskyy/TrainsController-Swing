package com.ziobrowski.models;

import com.ziobrowski.Main;
import com.ziobrowski.trains.*;

import javax.swing.table.DefaultTableModel;

public class TrainModel extends DefaultTableModel {
//    Object[] columns = {"ID", "Name", "Type", "State", "Capacity", "Train station", "Travel time"};

    public TrainModel(int rowCount, Object[] headers) {
        super(rowCount, headers.length);
        setColumnIdentifiers(headers);
        initializeData();
    }

    void initializeData() {
        Main.controller.trains.forEach(train -> addRow(train.toObjectArray()));
    }

    public void addRow(Train t) {
        Main.controller.addTrain(t);
        addRow(t.toObjectArray());
    }

    @Override
    public void removeRow(int row) {
        int id = (int) getValueAt(row, 0);
        String name = (String) getValueAt(row, 1);
        super.removeRow(row);
        Main.controller.removeTrainByIdAndName(id, name);
//        Main.controller.trains.remove(row);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > Main.controller.trains.size())
            return null;
        Train t = Main.controller.trains.get(rowIndex);
        switch (columnIndex) {
            case (0) -> {
                return t.getId();
            }
            case (1) -> {
                return t.name;
            }
            case (2) -> {
                return t.type;
            }
            case (3) -> {
                return t.state;
            }
            case (4) -> {
                return t.capacity;
            }
            case (5) -> {
                return t.current;
            }
            case (6) -> {
                return t.travelTime;
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Train t = Main.controller.trains.get(rowIndex);
        switch (columnIndex) {
            case (1) -> t.name = (String) aValue;
            case (2) -> t.type = (TrainType) aValue;
            case (3) -> t.state = (TrainState) aValue;
            case (4) -> t.capacity = Integer.parseInt((String) aValue);
            case (5) -> {
                TrainStation oldTs = t.current;
                TrainStation newTs = Main.controller.getStationByFullName((String) aValue);
                t.current = newTs;
                if (oldTs != null)
                    oldTs.removeTrain(t);
                if (newTs != null)
                    newTs.addTrain(t, t.travelTime);
            }
            case (6) -> t.travelTime = new Time((String) aValue);
        }
    }

}
