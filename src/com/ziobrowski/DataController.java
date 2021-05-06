package com.ziobrowski;

import com.ziobrowski.trains.CargoTrain;
import com.ziobrowski.trains.PassengerTrain;
import com.ziobrowski.trains.Train;
import com.ziobrowski.trains.TrainStation;

import java.util.*;

public class DataController {
    public final List<Train> trains = new ArrayList<>();
    public final List<TrainStation> trainStations = new ArrayList<>();

    public DataController() {
        addStation(new TrainStation("TS1", Utils.randomInt(5, 20)));
        addStation(new TrainStation("TS2", Utils.randomInt(5, 20)));
        addStation(new TrainStation("TS3", Utils.randomInt(5, 20)));

        addTrain(new CargoTrain("Cargo train 1", Utils.randomInt(500, 1000), trainStations.get(0)));
        addTrain(new CargoTrain("Cargo train 2", Utils.randomInt(500, 1000), trainStations.get(0)));
        addTrain(new CargoTrain("Cargo train 3", Utils.randomInt(500, 1000), null));
        addTrain(new CargoTrain("Cargo train 4", Utils.randomInt(500, 1000), trainStations.get(1)));

        addTrain(new PassengerTrain("Passenger train 1", Utils.randomInt(50, 100), trainStations.get(0)));
        addTrain(new PassengerTrain("Passenger train 2", Utils.randomInt(50, 100), null));
        addTrain(new PassengerTrain("Passenger train 3", Utils.randomInt(50, 100), trainStations.get(1)));
        addTrain(new PassengerTrain("Passenger train 4", Utils.randomInt(50, 100), trainStations.get(0)));
        addTrain(new PassengerTrain("Passenger train 5", Utils.randomInt(50, 100), null));
    }

    public void addTrain(Train t) {
        if (t.current != null) {
            t.current.addTrain(t,t.travelTime);
        }
        trains.add(t);
    }

    public TrainStation getStationByIdAndName(int id, String name) {
        Optional<TrainStation> found = trainStations.stream().filter(it -> it.getId() == id && it.name.compareTo(name) == 0).findFirst();
        return found.orElse(null);
    }

    public TrainStation getStationByFullName(String fullName) {
        String[] split = fullName.split("@");
        int id = Integer.parseInt(split[0]);
        String name = split[1];
        return getStationByIdAndName(id, name);
    }


    public void removeStationByIdAndName(int id, String name) {
        trainStations.removeIf(it -> it.getId() == id && it.name.compareTo(name) == 0);
        trains.forEach(it -> {
            if (it.current != null && it.current.getId() == id && it.current.name.compareTo(name) == 0)
                it.current = null;
        });
    }

    public void removeTrainByIdAndName(int id, String name) {
        removeTrain(findTrainByIdAndName(id, name));
    }
    public Train findTrainByFullName(String fullName) {
        String[] sliced = fullName.split("@");
        int id = Integer.parseInt(sliced[0]);
        String name = sliced[1];
        return findTrainByIdAndName(id, name);
    }
    public Train findTrainByIdAndName(int id, String name) {
        Optional<Train> found = trains.stream()
                .filter(it -> it.getId() == id && it.name.compareTo(name) == 0)
                .findFirst();
        return found.orElse(null);
    }

    public void removeTrain(Train t) {
        trains.remove(t);
        trainStations.forEach(ts -> ts.removeTrain(t));
    }

    public void addStation(TrainStation ts) {
        trainStations.add(ts);
    }
}
