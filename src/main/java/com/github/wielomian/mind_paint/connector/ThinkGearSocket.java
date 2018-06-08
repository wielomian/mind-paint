package com.github.wielomian.mind_paint.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;

/**
 * Created by Jan Tulowiecki on 2018-06-08.
 * Based on http://developer.neurosky.com/docs/lib/exe/fetch.php?media=app_notes:thinkgear_socket_protocol.pdf
 */
public class ThinkGearSocket {

    private final Socket socket;
    private final PrintWriter writer;
    private final BufferedReader reader;

    public ThinkGearSocket(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        writer = new PrintWriter(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        writer.write("{\"appName\": \"Mind Paint\", \"appKey\": \"94e23c48e6885e6db360fbb53fb1da17a1b514e7\"}");
        String line = reader.readLine();
        if (line.contains("\"isAuthorized\": false")){
            throw new IOException("Socket not authorised");
        }
        writer.write("{\"enableRawOutput\": false, \"format\": \"Json\"}");
    }

    public Optional<Measurement> getMeasurement(){
        try {
            if (!reader.ready()){
                return Optional.empty();
            }
            System.out.println(reader.readLine());
            return Optional.of(new Measurement(Math.random(), Math.random(), Math.random()));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
