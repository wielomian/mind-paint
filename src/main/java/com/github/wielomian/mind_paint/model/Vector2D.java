package com.github.wielomian.mind_paint.model;

/**
 * Created by Jan Tulowiecki on 2018-04-28.
 */
public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void rotate(double angle) {
        double rotSin = Math.sin(angle);
        double rotCos = Math.cos(angle);
        double previousX = x;
        double previousY = y;

        x = previousX * rotCos - previousY * rotSin;
        y = previousX * rotSin + previousY * rotCos;
    }
}
