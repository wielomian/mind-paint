package com.github.wielomian.mind_paint.connector;

import java.util.Optional;
import java.util.Random;

/**
 * Created by Jan Tulowiecki on 2018-05-31.
 */
public class RandomDataStreamFactory implements DataStreamFactory {

    @Override
    public RandomDataStream createDataStream() {
        return new RandomDataStream();
    }

    private static class RandomDataStream implements DataStream {
        private final Random random = new Random();

        @Override
        public Optional<Measurement> getMeasurement() {
            return Optional.of(new Measurement(random.nextDouble(), random.nextDouble(), random.nextDouble()));
        }
    }
}
