package com.github.wielomian.mind_paint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("../../../../picture_view.fxml"));
        URL pictureViewResourceURL = getClass().getClassLoader().getResource("picture_view.fxml");
        if (pictureViewResourceURL == null){
            throw new IOException("Couldn't find Picture View FXML file");
        }
        Parent root = FXMLLoader.load(pictureViewResourceURL);
        primaryStage.setTitle("MindPint");
        primaryStage.setScene(new Scene(root, 960, 550));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
