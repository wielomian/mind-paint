package com.github.wielomian.tgc_mock.controller;

import com.github.wielomian.tgc_mock.handler.SocketHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Jan Tulowiecki on 2018-08-15.
 */
public class ServiceController implements Initializable {

    @FXML
    private ListView<String> connections;

    @FXML
    private ListView<String> logs;

    private SenderService senderService = new SenderService();
    private ObservableList<String> connectionNames = FXCollections.observableArrayList();
    private Map<String, SocketHandler> connectionToSocketHandlerMapping = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        attachConnectionsListeners();
        startServerSocketDaemon();
        startMessageHandlingDaemon();
    }

    private void attachConnectionsListeners() {
        connections.setItems(connectionNames);
        connections.getSelectionModel().selectedItemProperty().addListener((ign1, ign2, newValue) -> {
            SocketHandler socketHandler = connectionToSocketHandlerMapping.get(newValue);
            if (socketHandler == null){
                logs.setItems(FXCollections.emptyObservableList());
            } else {
                logs.setItems(FXCollections.observableList(socketHandler.getLog()));
            }
        });
    }

    private void startMessageHandlingDaemon() {
        Thread senderServiceThread = new Thread(new SenderServiceRunnable(senderService));
        senderServiceThread.setDaemon(true);
        senderServiceThread.start();
    }

    private void startServerSocketDaemon() {
        Thread serverSocketThread = new Thread(() -> {
            try {
                this.startService();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
        serverSocketThread.setDaemon(true);
        serverSocketThread.start();
    }

    private void startService() throws IOException, InterruptedException {
        try (ServerSocket serverSocket = new ServerSocket(13854)) {
            while (true) {
                Socket accept = serverSocket.accept();
                SocketHandler socketHandler = new SocketHandler(accept);
                String socketName = accept.toString();
                senderService.register(socketHandler);
                connectionToSocketHandlerMapping.put(socketName, socketHandler);
                connectionNames.add(socketName);
            }
        }
    }
}
