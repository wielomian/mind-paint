package com.github.wielomian.mind_paint.connector;

import com.github.wielomian.mind_paint.configuration.MeasurementType;

/**
 * Created by Jan Tulowiecki on 2018-05-31.
 */
public class Measurement {
    double lowAlpha;
    double highAlpha;
    double lowBeta;
    double highBeta;
    double meditation;
    double attention;

    public Measurement(double lowAlpha, double highAlpha, double lowBeta, double highBeta, double meditation, double attention) {
        this.lowAlpha = lowAlpha;
        this.highAlpha = highAlpha;
        this.lowBeta = lowBeta;
        this.highBeta = highBeta;
        this.meditation = meditation;
        this.attention = attention;
    }

    public Measurement() {

    }

    public double getForType(MeasurementType measurementType) {
        switch (measurementType) {
            case LOW_ALPHA:
                return lowAlpha;
            case HIGH_ALPHA:
                return highAlpha;
            case LOW_BETA:
                return lowBeta;
            case HIGH_BETA:
                return highBeta;
            case ATTENTION:
                return attention;
            case MEDITATION:
                return meditation;
            default:
                throw new RuntimeException("Unrecognized Measurement Type");
        }
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "lowAlpha=" + lowAlpha +
                ", highAlpha=" + highAlpha +
                ", lowBeta=" + lowBeta +
                ", highBeta=" + highBeta +
                ", meditation=" + meditation +
                ", attention=" + attention +
                '}';
    }
}
