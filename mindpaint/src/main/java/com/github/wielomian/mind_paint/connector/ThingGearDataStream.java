package com.github.wielomian.mind_paint.connector;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Jan Tulowiecki on 2018-06-10.
 */
public class ThingGearDataStream implements AutoCloseable {

    //TODO: move to properties
    public static final int MAX_USAGES = 15;
    private final ThinkGearSocket thinkGearSocket;
    private Measurement lastMeasurement;
    private int lastMeasurementUsages;

    public ThingGearDataStream() throws IOException {
        thinkGearSocket = new ThinkGearSocket();
    }

    public Optional<Measurement> getMeasurement() {
        Optional<Measurement> measurement = thinkGearSocket.getMeasurement();
        if (measurement.isPresent()) {
            lastMeasurement = measurement.get();
            lastMeasurementUsages = 0;
        }
        if (lastMeasurementUsages == MAX_USAGES) {
            return Optional.empty();
        } else {
            lastMeasurementUsages++;
            return Optional.ofNullable(lastMeasurement);
        }
    }

    @Override
    public void close() throws Exception {
        thinkGearSocket.close();
    }
}

