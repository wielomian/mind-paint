package com.github.wielomian.mind_paint.connector;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.Optional;

/**
 * Created by Jan Tulowiecki on 2018-06-08.
 * Based on http://developer.neurosky.com/docs/lib/exe/fetch.php?media=app_notes:thinkgear_socket_protocol.pdf
 */
public class ThinkGearSocket implements Closeable {

    private final Socket socket;
    private final PrintWriter socketWriter;
    private final BufferedReader socketReader;
    private final Gson gson;

    public ThinkGearSocket(boolean requireJsonMessage) throws IOException{
        socket = new Socket("localhost", 13854);
        socketWriter = new PrintWriter(socket.getOutputStream(), true);
        socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        if (requireJsonMessage) {
            String jsonCommand = "{\"enableRawOutput\": false, \"format\": \"Json\"}\n";
            socketWriter.println(jsonCommand);
        }
        gson = new Gson();
    }

    public ThinkGearSocket() throws IOException {
        this(false);
    }

    public Optional<Measurement> getMeasurement() {
        try {
            if (!socketReader.ready()) {
                return Optional.empty();
            }
            String line = socketReader.readLine();
            //TODO: remove this println
            System.out.println(line);
            Measurement measurement = null;
            if (line.contains("eegPower")) {
                MeasurementJson measurementJson = gson.fromJson(line, MeasurementJson.class);
                measurement = new Measurement();
                measurementJson.fillMeasurement(measurement);
            }
            return Optional.ofNullable(measurement);
        } catch (IOException e){
            //TODO: remove all the printStackTrace, use logger
            e.printStackTrace();
            return Optional.empty();
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
                if (socketWriter != null){
                    socketWriter.close();
                }
            } finally {
                if (socketReader != null){
                    socketReader.close();
                }
            }
        }
    }

    private static final Average ATTENTION_AVERAGE = new Average();
    private static final Average MEDITATION_AVERAGE = new Average();
    private static final Average LOW_ALPHA_AVERAGE = new Average();
    private static final Average HIGH_ALPHA_AVERAGE = new Average();
    private static final Average LOW_BETA_AVERAGE = new Average();
    private static final Average HIGH_BETA_AVERAGE = new Average();

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
                    ATTENTION_AVERAGE.add(eSense.attention);
                    measurement.attention = normalize(eSense.attention, ATTENTION_AVERAGE);
                }
                if (eSense.meditation != 0) {
                    MEDITATION_AVERAGE.add(eSense.meditation);
                    measurement.meditation = normalize(eSense.meditation, MEDITATION_AVERAGE);
                }
            }
            if (eegPower != null) {
                if (eegPower.lowAlpha != 0) {
                    LOW_ALPHA_AVERAGE.add(eegPower.lowAlpha);
                    measurement.lowAlpha = normalize(eegPower.lowAlpha, LOW_ALPHA_AVERAGE);
                }
                if (eegPower.highAlpha != 0) {
                    HIGH_ALPHA_AVERAGE.add(eegPower.highAlpha);
                    measurement.highAlpha = normalize(eegPower.highAlpha, HIGH_ALPHA_AVERAGE);
                }
                if (eegPower.lowBeta != 0) {
                    LOW_BETA_AVERAGE.add(eegPower.lowBeta);
                    measurement.lowBeta = normalize(eegPower.lowBeta, LOW_BETA_AVERAGE);
                }
                if (eegPower.highBeta != 0) {
                    HIGH_BETA_AVERAGE.add(eegPower.highBeta);
                    measurement.highBeta = normalize(eegPower.highBeta, HIGH_BETA_AVERAGE);
                }
            }
        }

        private static double normalize(double value, Average average){
            return Math.min(value / (2 * average.get()), 1.0);
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
