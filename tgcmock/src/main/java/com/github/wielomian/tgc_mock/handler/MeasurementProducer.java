package com.github.wielomian.tgc_mock.handler;

import java.util.Objects;
import java.util.Random;

/**
 * Created by Jan Tulowiecki on 2018-07-12.
 */
public class MeasurementProducer {

    private final static String [] eegPowerParameters = {"delta", "theta", "lowAlpha", "lowBeta", "highAlpha", "highBeta"};
    private final static String [] eSenseParameters = {"attention", "meditation"};

    private final Random random = new Random();

    public String getRandomMeasurement(){
        StringBuilder measurementBuilder = new StringBuilder("{");
        appendRandomMeasurements(measurementBuilder, "eSense", eSenseParameters, 90);
        measurementBuilder.append(',');
        appendRandomMeasurements(measurementBuilder, "eegPower", eegPowerParameters, 200000);
        measurementBuilder.append('}');
        return measurementBuilder.toString();
    }

    private void appendRandomMeasurements(StringBuilder resultBuilder, String typeName, String [] parameters, int limit){
        resultBuilder
                .append('\"')
                .append(typeName)
                .append("\":{");
        for (String parameter : parameters){
            resultBuilder.append('\"')
                    .append(parameter)
                    .append("\":")
                    .append(random.nextInt(limit) + 1);
            if (!Objects.equals(parameter, parameters[parameters.length - 1])){
                resultBuilder.append(',');
            }
        }
        resultBuilder.append('}');
    }


    public static void main(String[] args) {
        System.out.println(new MeasurementProducer().getRandomMeasurement());
    }
}
