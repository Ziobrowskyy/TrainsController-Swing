package com.ziobrowski.trains;

public class PassengerTrain extends Train {

    public PassengerTrain(String name, int capacity, TrainStation current) {
        this(TrainState.NEW, name, capacity, current, new Time(1, 0));
    }
    public PassengerTrain(String name, int capacity, TrainStation current, Time travelTime) {
        this(TrainState.NEW, name, capacity, current, travelTime);
    }
    public PassengerTrain(TrainState state, String name, int capacity, TrainStation current, Time travelTime) {
        super(TrainType.PASSENGER, state, name, capacity, current, travelTime);
    }
}
