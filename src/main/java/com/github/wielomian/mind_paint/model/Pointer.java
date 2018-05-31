package com.github.wielomian.mind_paint.model;


import javafx.scene.paint.Color;

/**
 * Created by Jan Tulowiecki on 2018-04-27.
 */
public class Pointer {

    private double radius;
    private double x;
    private double y;
    private boolean traceEnabled = true;
    private Color color = Color.rgb(127, 0, 95);
    private final Vector2D velocity;

    public Pointer(double radius, int x, int y, int dx, int dy) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        velocity = new Vector2D(dx, dy);
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public double getRadius() {
        return radius;
    }

    public void move() {
        x += velocity.getX();
        y += velocity.getY();
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isTraceEnabled() {
        return traceEnabled;
    }

    public void setTraceEnabled(boolean traceEnabled) {
        this.traceEnabled = traceEnabled;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
