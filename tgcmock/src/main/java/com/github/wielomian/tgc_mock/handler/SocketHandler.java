package com.github.wielomian.tgc_mock.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan Tulowiecki on 2018-07-12.
 */
public class SocketHandler implements AutoCloseable {

    private final Socket socket;
    private final PrintWriter writer;
    private final BufferedReader reader;
    private boolean sendEnabled = true;
    private boolean logEnabled = true;
    private List<String> log = new ArrayList<>();

    public SocketHandler(Socket socket) throws IOException {
        this.socket = socket;
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void send(String message) {
        if (logEnabled) {
            log.add("[send]: " + message);
        }
        writer.println(message);
    }

    public List<String> getLog() {
        return log;
    }

    public void receiveAll() {
        try {
            while (reader.ready()) {
                if (logEnabled) {
                    log.add("[recived]: " + reader.readLine());
                }
            }
        } catch (IOException e) {
            //TODO: logs
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        try {
            if (socket != null) {
                socket.close();
            }
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
    }

    public synchronized boolean isSendEnabled() {
        return sendEnabled;
    }

    public synchronized void setSendEnabled(boolean sendEnabled) {
        log.add("ENABLE SENDING SET TO: " + sendEnabled);
        this.sendEnabled = sendEnabled;
    }

    public boolean isLogEnabled() {
        return logEnabled;
    }

    public void setLogEnabled(boolean logEnabled) {
        log.add("ENABLE LOGS SET TO: " + logEnabled);
        this.logEnabled = logEnabled;
    }
}
