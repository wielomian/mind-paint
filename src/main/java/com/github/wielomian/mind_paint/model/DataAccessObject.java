package com.github.wielomian.mind_paint.model;

import com.github.wielomian.mind_paint.connector.DataStream;
import com.github.wielomian.mind_paint.connector.RandomDataStreamFactory;

/**
 * Created by Jan Tulowiecki on 2018-04-27.
 */
public class DataAccessObject {

    private final PictureSetup pictureSetup;
    private final DataStream dataStream;

    private DataAccessObject() {
        pictureSetup = new PictureSetup(600, 376);
        Pointer one = new Pointer(5, 20, 350, 0.8, -0.8);
        Pointer two = new Pointer(5, 20, 350, 1, -1);
        Pointer three = new Pointer(5, 20, 350, 1.25, -1.25);
        Pointer four = new Pointer(5, 20, 350, 1.6, -1.6);
        one.setBrightnessSensitivity(-0.6);
        two.setBrightnessSensitivity(-0.5);
        three.setBrightnessSensitivity(-0.4);
        four.setBrightnessSensitivity(-0.3);
        pictureSetup.getPointers().add(one);
        pictureSetup.getPointers().add(two);
        pictureSetup.getPointers().add(three);
        pictureSetup.getPointers().add(four);
        dataStream = new RandomDataStreamFactory().createDataStream();
    }

    private static DataAccessObject instance = new DataAccessObject();

    public static DataAccessObject getInstance() {
        return instance;
    }

    public PictureSetup getPictureSetup() {
        return pictureSetup;
    }

    public DataStream getDataStream() {
        return dataStream;
    }
}
