package com.github.wielomian.mind_paint.connector;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Jan Tulowiecki on 2018-06-10.
 */
public class ThinkGearDataStreamFactory implements DataStreamFactory {

    @Override
    public DataStream createDataStream() throws IOException {
        return new ThingGearDataStream();
    }

    private static final class ThingGearDataStream implements DataStream {

        private final ThinkGearSocket thinkGearSocket;

        ThingGearDataStream() throws IOException {
            thinkGearSocket = new ThinkGearSocket("localhost", 13854);
        }

        @Override
        public Optional<Measurement> getMeasurement() {
            return thinkGearSocket.getMeasurement();
        }
    }
}
