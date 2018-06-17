package com.github.wielomian.mind_paint.connector;

import com.github.wielomian.mind_paint.configuration.MeasurementType;

/**
 * Created by Jan Tulowiecki on 2018-05-31.
 */
public class Measurement {
    final double alpha;
    final double beta;
    final double theta;

    public Measurement(double alpha, double beta, double theta) {
        this.alpha = alpha;
        this.beta = beta;
        this.theta = theta;
    }

    public double getForType(MeasurementType measurementType) {
        switch (measurementType) {
            case ALPHA:
                return alpha;
            case BETA:
                return beta;
            case THETA:
                return theta;
            default:
                throw new RuntimeException("Unrecognized Measurement Type");
        }
    }
}
