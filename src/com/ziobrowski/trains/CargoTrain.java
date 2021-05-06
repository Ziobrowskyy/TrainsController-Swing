package com.ziobrowski.trains;

public class CargoTrain extends Train {
    public CargoTrain(String name, int capacity, TrainStation current) {
        this(TrainState.NEW, name, capacity, current, new Time(1, 0));
    }
    public CargoTrain(String name, int capacity, TrainStation current, Time travelTime) {
        this(TrainState.NEW, name, capacity, current, travelTime);
    }
    public CargoTrain(TrainState state, String name, int capacity, TrainStation current, Time travelTime) {
        super(TrainType.CARGO, state, name, capacity, current, travelTime);
    }
}
