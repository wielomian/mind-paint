package com.github.wielomian.mind_paint.connector;

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

    public double getAlpha() {
        return alpha;
    }

    public double getBeta() {
        return beta;
    }

    public double getTheta() {
        return theta;
    }
}
