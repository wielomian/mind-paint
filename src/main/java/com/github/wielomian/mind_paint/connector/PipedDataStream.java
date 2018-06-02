package com.github.wielomian.mind_paint.connector;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Created by Jan Tulowiecki on 2018-05-31.
 */
public abstract class PipedDataStream implements DataStream {

    private final Deque<Measurement> measurementDeque;
    private int maxQueueSize;

    PipedDataStream(int maxQueueSize) {
        if (maxQueueSize < 1) {
            throw new IllegalArgumentException("Queue must have at least 1 element");
        }
        measurementDeque = new LinkedList<>();
        this.maxQueueSize = maxQueueSize;
    }

    public synchronized Optional<Measurement> getMeasurement() {
        return Optional.ofNullable(measurementDeque.pollFirst());
    }

    synchronized void appendMeasurement(Measurement measurement) {
        if (measurementDeque.size() == maxQueueSize) {
            measurementDeque.pollFirst();
        }
        measurementDeque.addLast(measurement);
    }
}
