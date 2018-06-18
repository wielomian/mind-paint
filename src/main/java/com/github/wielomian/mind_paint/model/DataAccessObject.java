package com.github.wielomian.mind_paint.model;

import com.github.wielomian.mind_paint.configuration.Configuration;
import com.github.wielomian.mind_paint.connector.DataStream;
import com.github.wielomian.mind_paint.connector.RandomDataStreamFactory;
import com.github.wielomian.mind_paint.connector.ThinkGearDataStreamFactory;
import com.github.wielomian.mind_paint.connector.ThinkGearSocket;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Jan Tulowiecki on 2018-04-27.
 */
public class DataAccessObject {

    private final PictureSetup pictureSetup;
    private final DataStream dataStream;
    private final Configuration configuration;
    private Stage window;

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
        configuration = new Configuration();
        DataStream localDataStream;
        try {
            localDataStream = new ThinkGearDataStreamFactory().createDataStream();
        } catch (IOException e) {
            e.printStackTrace();
            localDataStream = new RandomDataStreamFactory().createDataStream();
        }
        dataStream = localDataStream;
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

    public Configuration getConfiguration() {
        return configuration;
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }
}
