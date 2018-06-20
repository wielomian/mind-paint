package com.github.wielomian.mind_paint.connector;

import com.google.gson.Gson;

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
    private final Gson gson;
    private Measurement measurement;

    public ThinkGearSocket() throws IOException {
        channel = SocketChannel.open(new InetSocketAddress("localhost", 13854));

        CharsetEncoder enc = Charset.forName("US-ASCII").newEncoder();
        scanner = new Scanner(channel);
        String jsonCommand = "{\"enableRawOutput\": false, \"format\": \"Json\"}\n";
        channel.write(enc.encode(CharBuffer.wrap(jsonCommand)));
        gson = new Gson();
    }

    public Optional<Measurement> getMeasurement() {
        if (!scanner.hasNext()) {
            return Optional.empty();
        }
        String line = scanner.nextLine();
        System.out.println(line);
        if (line.contains("eegPower")) {
            MeasurementJson measurementJson = gson.fromJson(line, MeasurementJson.class);
            if (measurement == null){
                measurement = new Measurement();
            }
            measurementJson.fillMeasurement(measurement);
        }
        return Optional.ofNullable(measurement);
    }

    private static final class MeasurementJson {
        private int poorSignalLevel;
        private ESenseJson eSense;
        private EegPowerJson eegPower;

        public MeasurementJson(int poorSignalLevel, ESenseJson eSense, EegPowerJson eegPower) {
            this.poorSignalLevel = poorSignalLevel;
            this.eSense = eSense;
            this.eegPower = eegPower;
        }

        public MeasurementJson() {
        }

        public void fillMeasurement(Measurement measurement) {
            if (eSense != null) {
                if (eSense.attention != 0) {
                    measurement.attention = Math.min(eSense.attention / 1000000.0, 1.0);
                }
                if (eSense.meditation != 0) {
                    measurement.meditation = Math.min(eSense.meditation / 1000000.0, 1.0);
                }
            }
            if (eegPower != null) {
                if (eegPower.lowAlpha != 0) {
                    measurement.lowAlpha = Math.min(eegPower.lowAlpha / 1000000.0, 1.0);
                }
                if (eegPower.highAlpha != 0) {
                    measurement.highAlpha = Math.min(eegPower.highAlpha / 1000000.0, 1.0);
                }
                if (eegPower.lowBeta != 0) {
                    measurement.lowBeta = Math.min(eegPower.lowBeta / 1000000.0, 1.0);
                }
                if (eegPower.highBeta != 0) {
                    measurement.highBeta = Math.min(eegPower.highBeta / 1000000.0, 1.0);
                }
            }
        }
    }

    private static final class ESenseJson {
        private int attention;
        private int meditation;

        public ESenseJson(int attention, int meditation) {
            this.attention = attention;
            this.meditation = meditation;
        }

        public ESenseJson() {
        }
    }

    private static final class EegPowerJson {
        private int delta;
        private int theta;
        private int lowAlpha;
        private int highAlpha;
        private int lowBeta;
        private int highBeta;
        private int lowGamma;
        private int highGamma;

        public EegPowerJson() {
        }

        public EegPowerJson(int delta, int theta, int lowAlpha, int highAlpha, int lowBeta, int highBeta, int lowGamma, int highGamma) {
            this.delta = delta;
            this.theta = theta;
            this.lowAlpha = lowAlpha;
            this.highAlpha = highAlpha;
            this.lowBeta = lowBeta;
            this.highBeta = highBeta;
            this.lowGamma = lowGamma;
            this.highGamma = highGamma;
        }
    }
}
