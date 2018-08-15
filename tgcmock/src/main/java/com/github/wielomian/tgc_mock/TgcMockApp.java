package com.github.wielomian.tgc_mock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Jan Tulowiecki on 2018-07-12.
 */
public class TgcMockApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL view = getClass().getClassLoader().getResource("tgc_mock.fxml");
        if (view == null) {
            throw new IOException("Couldn't find TGC Mock FXML file");
        }
        Parent root = FXMLLoader.load(view);
        primaryStage.setTitle("ThinkGear Connector Mock");
        primaryStage.setScene(new Scene(root, 960, 550));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
