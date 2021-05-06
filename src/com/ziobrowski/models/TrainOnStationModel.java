package com.ziobrowski.models;

import com.ziobrowski.Main;
import com.ziobrowski.trains.*;

import javax.swing.table.DefaultTableModel;

public class TrainOnStationModel extends DefaultTableModel {
//    Object[] columns = {"ID", "Name", "Type", "State", "Capacity", "Train station", "Travel time"};
final TrainStation trainStation;
    public TrainOnStationModel(int rowCount, Object[] headers, TrainStation ts) {
        super(rowCount, headers.length);
        trainStation = ts;
        setColumnIdentifiers(headers);
        initializeData();
    }

    void initializeData() {
        System.out.println(trainStation);
        trainStation.trains.forEach((train, times)-> {
            System.out.println(train);
            times.forEach(time -> addRow(new Object[]{train.getId(), train.name, train.type, train.state, train.capacity, time}));
        });
//        Main.controller.trains.forEach(train -> addRow(train.toObjectArray()));
    }

    public void addRow(Train train, Time time) {
        trainStation.addTrain(train, time);
        addRow(new Object[]{train.getId(), train.name, train.type, train.state, train.capacity, time});
    }

    @Override
    public void removeRow(int row) {
        int id = (int) getValueAt(row, 0);
        String name = (String) getValueAt(row, 1);
        Train train = Main.controller.findTrainByIdAndName(id, name);
        Time time = (Time) getValueAt(row, 5);
        trainStation.removeTrainAtGivenTime(train, time);
        super.removeRow(row);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > Main.controller.trains.size())
            return null;
        int i = 0;
        Train selTrain = null;
        Time selTime = null;

        outer:
        for(Train tr : trainStation.trains.keySet()) {
            for(Time ti : trainStation.trains.get(tr)) {
                if(i == rowIndex) {
                    selTrain = tr;
                    selTime = ti;
                    break outer;
                }
                i++;
            }
        }
        if(selTime == null || selTrain == null)
            return null;

        switch (columnIndex) {
            case (0) -> {
                return selTrain.getId();
            }
            case (1) -> {
                return selTrain.name;
            }
            case (2) -> {
                return selTrain.type;
            }
            case (3) -> {
                return selTrain.state;
            }
            case (4) -> {
                return selTrain.capacity;
            }
            case (5) -> {
                return selTime;
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
