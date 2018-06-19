package com.github.wielomian.mind_paint.connector;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Jan Tulowiecki on 2018-06-10.
 */
public class ThinkGearDataStreamFactory implements DataStreamFactory {

    public static final int MAX_USAGES = 5;

    @Override
    public DataStream createDataStream() throws IOException {
        return new ThingGearDataStream();
    }

    private static final class ThingGearDataStream implements DataStream {

        private final ThinkGearSocket thinkGearSocket;
        private Measurement lastMeasurement;
        private int lastMeasurementUsages;

        ThingGearDataStream() throws IOException {
            thinkGearSocket = new ThinkGearSocket();
        }

        @Override
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
    }
}
