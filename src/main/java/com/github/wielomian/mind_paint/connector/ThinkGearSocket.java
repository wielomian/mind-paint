package com.github.wielomian.mind_paint.connector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Optional;
import java.util.Scanner;

/**
 * Created by Jan Tulowiecki on 2018-06-08.
 * Based on http://developer.neurosky.com/docs/lib/exe/fetch.php?media=app_notes:thinkgear_socket_protocol.pdf
 */
public class ThinkGearSocket {

    private final SocketChannel channel;
    private final Scanner scanner;

    public ThinkGearSocket() throws IOException {
        channel = SocketChannel.open(new InetSocketAddress("localhost", 13854));

        CharsetEncoder enc = Charset.forName("US-ASCII").newEncoder();
        scanner = new Scanner(channel);
        String jsonCommand = "{\"enableRawOutput\": false, \"format\": \"Json\"}\n";
        channel.write(enc.encode(CharBuffer.wrap(jsonCommand)));

    }

    public Optional<Measurement> getMeasurement() {
        if (!scanner.hasNext()) {
            return Optional.empty();
        }
        String line = scanner.nextLine();
        System.out.println(line);
        return Optional.of(new Measurement(Math.random(), Math.random(), Math.random()));
    }
}
