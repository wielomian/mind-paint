package com.github.wielomian.mind_paint.connector;

import java.util.Optional;

/**
 * Created by Jan Tulowiecki on 2018-05-31.
 */
public interface DataStream {

    Optional<Measurement> getMeasurement();
}
