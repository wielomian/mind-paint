package com.github.wielomian.mind_paint.model;

import com.github.wielomian.mind_paint.configuration.Configuration;
import com.github.wielomian.mind_paint.connector.ThingGearDataStream;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Jan Tulowiecki on 2018-04-27.
 */
public class DataAccessObject {

    private final PictureSetup pictureSetup;
    private ThingGearDataStream dataStream;
    private final Configuration configuration;
    private Stage configurationWindow;
    private Stage aboutWindow;
    private boolean connected = false;

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
        reconnect();
    }

    private static DataAccessObject instance = new DataAccessObject();

    public static DataAccessObject getInstance() {
        return instance;
    }

    public PictureSetup getPictureSetup() {
        return pictureSetup;
    }

    public ThingGearDataStream getDataStream() {
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
        try {
            dataStream = new ThingGearDataStream();
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
