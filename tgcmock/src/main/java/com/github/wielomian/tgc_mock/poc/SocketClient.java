package com.github.wielomian.tgc_mock.poc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Jan Tulowiecki on 2018-08-12.
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 13854);
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            printWriter.write("Say hello!");
            System.out.println("Said hello!");
            while(true) {
                if (reader.ready())
                System.out.println("Read line:" + reader.readLine());
            }
        }
    }
}
