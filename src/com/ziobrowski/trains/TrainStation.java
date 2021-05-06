package com.ziobrowski.trains;

import com.ziobrowski.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrainStation {
    private final int id;
    public String name;
    public final Map<Train, List<Time>> trains = new HashMap<>();
    public int maximalCapacity;

    public TrainStation(String name, int capacity) {
        this.id = Utils.randomInt(0,100000);
        this.name = name;
        this.maximalCapacity = capacity;
    }

    public int getId() {
        return id;
    }

    public boolean addTrain(Train train, Time time) {
        if (trains.size() < maximalCapacity) {
            trains.putIfAbsent(train, new ArrayList<>());
            trains.get(train).add(time);
            trains.get(train).sort(Time::compareTo);
            return true;
        }
        System.out.println("Cannot add new train - station is at full capacity!");
        return false;
    }

    public boolean addTrain(Train train, int hour, int minute) {
        return addTrain(train, new Time(hour, minute));
    }

    public boolean addTrain(Train train) {
        return this.addTrain(train, 13, 37);
    }

    public void removeTrainAtGivenTime(Train train, Time time) {
        if(trains.get(train) == null)
            return;
        if(trains.get(train).remove(time)) {
            train.current = null;
        }
        if(trains.get(train).isEmpty()) {
            trains.remove(train);
        }
    }

    public Train findTrainByName(String name) {
        for (Train train : trains.keySet()) {
            if (train.name.compareTo(name) == 0)
                return train;
        }
        System.out.println("Couldn't find train of given name!");
        return null;
    }

    public List<Train> findTrainsByName(String name) {
        return this.trains.keySet()
                .stream()
                .filter(train -> train.name.contains(name))
                .collect(Collectors.toList());
    }


    public List<Train> findTrainsByState(TrainState state) {
        return this.trains.keySet()
                .stream()
                .filter(train -> train.state == state)
                .collect(Collectors.toList());
    }

    public List<Train> getSortedByName() {
        return this.trains.keySet()
                .stream()
                .sorted(Train::compareTo)
                .collect(Collectors.toList());
    }

    public List<Train> getSortedByDepartures() {
        return this.trains.entrySet()
                .stream()
                .sorted((p1, p2) -> Integer.compare(p2.getValue().size(), p1.getValue().size()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public boolean removeTrain(Train train) {
        return trains.remove(train) == null;
    }

    public Object[] toObjectArray() {
        return new Object[]{id, name, maximalCapacity, getCurrentLoad()};
    }

    @Override
    public String toString() {
        return id+"@"+name;
    }

    public int getCurrentLoad() {
        int sumOfTrains = 0;
        for (List<Time> times : trains.values()) {
            sumOfTrains += times.size();
        }
        return sumOfTrains;
//        return trains.size();
    }

}
