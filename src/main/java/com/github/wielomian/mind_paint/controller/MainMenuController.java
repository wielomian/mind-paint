package com.github.wielomian.mind_paint.controller;

import com.github.wielomian.mind_paint.model.DataAccessObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Jan Tulowiecki on 2018-05-20.
 */
public class MainMenuController {

    public void onNewPictureButtonClicked() throws IOException {
        URL pictureViewResourceURL = getClass().getClassLoader().getResource("picture_view.fxml");
        if (pictureViewResourceURL == null) {
            throw new IOException("Couldn't find Picture View FXML file");
        }
        Parent root = FXMLLoader.load(pictureViewResourceURL);
        DataAccessObject.getInstance().getWindow().setScene(new Scene(root, 960, 550));
    }

    public void onSettingsButtonClicked() throws IOException {
        URL settingsViewResourceURL = getClass().getClassLoader().getResource("config.fxml");
        if (settingsViewResourceURL == null) {
            throw new IOException("Couldn't find Settings FXML file");
        }
        Parent root = FXMLLoader.load(settingsViewResourceURL);
        DataAccessObject.getInstance().getWindow().setScene(new Scene(root, 317, 476));
    }
}
