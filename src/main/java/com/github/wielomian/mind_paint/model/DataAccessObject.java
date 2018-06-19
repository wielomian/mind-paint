package com.github.wielomian.mind_paint.model;

import com.github.wielomian.mind_paint.configuration.Configuration;
import com.github.wielomian.mind_paint.connector.DataStream;
import com.github.wielomian.mind_paint.connector.RandomDataStreamFactory;
import com.github.wielomian.mind_paint.connector.ThinkGearDataStreamFactory;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Jan Tulowiecki on 2018-04-27.
 */
public class DataAccessObject {

    private final PictureSetup pictureSetup;
    private DataStream dataStream;
    private final Configuration configuration;
    private Stage configurationWindow;
    private Stage aboutWindow;
    private boolean connected = false;
    private final ThinkGearDataStreamFactory thinkGearDataStreamFactory;
    private final RandomDataStreamFactory randomDataStreamFactory;

    private DataAccessObject() {
        pictureSetup = new PictureSetup(600, 376);
        Pointer one = new Pointer(5, 20, 350, 0.8, -0.8);
        Pointer two = new Pointer(5, 20, 350, 1, -1);
        Pointer three = new Pointer(5, 20, 350, 1.25, -1.25);
        Pointer four = new Pointer(5, 20, 350, 1.6, -1.6);
        //one.setBrightnessSensitivity(-0.6);
        //two.setBrightnessSensitivity(-0.5);
        //three.setBrightnessSensitivity(-0.4);
        //four.setBrightnessSensitivity(-0.3);
        pictureSetup.getPointers().add(one);
        pictureSetup.getPointers().add(two);
        pictureSetup.getPointers().add(three);
        pictureSetup.getPointers().add(four);
        configuration = new Configuration();
        randomDataStreamFactory = new RandomDataStreamFactory();
        thinkGearDataStreamFactory = new ThinkGearDataStreamFactory();
        reconnect();
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

    public Stage getConfigurationWindow() {
        return configurationWindow;
    }

    public void setConfigurationWindow(Stage configurationWindow) {
        this.configurationWindow = configurationWindow;
    }

    public Stage getAboutWindow() {
        return aboutWindow;
    }

    public void setAboutWindow(Stage aboutWindow) {
        this.aboutWindow = aboutWindow;
    }

    public boolean isConnected() {
        return connected;
    }

    public void reconnect() {
        DataStream localDataStream;
        try {
            localDataStream = thinkGearDataStreamFactory.createDataStream();
            connected = true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            localDataStream = randomDataStreamFactory.createDataStream();
            connected = false;
        }
        if (localDataStream != null) {
            dataStream = localDataStream;
        }
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
