package com.github.wielomian.mind_paint.model;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by Jan Tulowiecki on 2018-04-27.
 */
public class Pointer {

    private static int counter = 0;

    private final int id;
    private boolean traceEnabled = true;
    private Color color = Color.hsb(180.0, 0.5, 0.5);
    private final Circle sprite;
    private final Vector2D velocity;

    // double from -1 to 0
    private double brightnessSensitivity = -0.5;

    public Pointer(double radius, double x, double y, double dx, double dy) {
        id = counter;
        counter++;
        velocity = new Vector2D(dx, dy);
        sprite = new Circle(x, y, radius);
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public double getRadius() {
        return sprite.getRadius();
    }

    public void move() {
        sprite.setCenterX(sprite.getCenterX() + velocity.getX());
        sprite.setCenterY(sprite.getCenterY() + velocity.getY());
    }

    public void setRadius(double radius) {
        sprite.setRadius(radius);
    }

    public double getX() {
        return sprite.getCenterX();
    }

    public double getY() {
        return sprite.getCenterY();
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

    public double getBrightnessSensitivity() {
        return brightnessSensitivity;
    }

    public void setBrightnessSensitivity(double brightnessSensitivity) {
        System.out.println("SET BRIGHTNESS: " + brightnessSensitivity + " FOR POINTER " + id);
        this.brightnessSensitivity = brightnessSensitivity;
    }

    public Circle getSprite() {
        return sprite;
    }

    public int getId() {
        return id;
    }
}
