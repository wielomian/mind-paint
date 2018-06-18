package com.github.wielomian.mind_paint;

import com.github.wielomian.mind_paint.model.DataAccessObject;
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
        DataAccessObject.getInstance().setWindow(primaryStage);
        URL menuViewResourceURL = getClass().getClassLoader().getResource("main_menu.fxml");
        if (menuViewResourceURL == null){
            throw new IOException("Couldn't find Main Menu View FXML file");
        }
        Parent root = FXMLLoader.load(menuViewResourceURL);
        primaryStage.setTitle("MindPint");
        primaryStage.setScene(new Scene(root, 317, 476));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
