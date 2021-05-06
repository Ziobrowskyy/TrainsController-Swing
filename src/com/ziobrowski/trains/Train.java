package com.ziobrowski.trains;

import com.ziobrowski.Utils;

abstract public class Train implements Comparable<Train> {
    private final int id;
    public TrainState state;
    public String name;
    public int capacity;
    public TrainStation current;
    public TrainType type;
    public Time travelTime;

    public Train(TrainType type, TrainState state, String name, int capacity, TrainStation current, Time travelTime) {
        this.id = Utils.randomInt(0,100000);
        this.type = type;
        this.state = state;
        this.name = name;
        this.capacity = capacity;
        this.current = current;
        this.travelTime = travelTime;
    }
    public int getId() {
        return id;
    }
    public Object[] toObjectArray() {
        return new Object[]{id, name, type, state, capacity, current, travelTime};
    }

    @Override
    public String toString() {
        return id + "@" + name;
    }

    @Override
    public int compareTo(Train o) {
        return this.name.compareTo(o.name);
    }

}
