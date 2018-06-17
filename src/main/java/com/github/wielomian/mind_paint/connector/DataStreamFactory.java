package com.github.wielomian.mind_paint.connector;

import java.io.IOException;

/**
 * Created by Jan Tulowiecki on 2018-05-31.
 */
public interface DataStreamFactory {

    DataStream createDataStream() throws IOException;
}
