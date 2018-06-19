package com.github.wielomian.mind_paint.engine;

import com.github.wielomian.mind_paint.model.Pointer;

/**
 * Created by Jan Tulowiecki on 2018-05-20.
 */
public class PositionUpdatingStrategy {
    private static final double EPS = 1e-7;

    private final int pictureWidth;
    private final int pictureHeight;

    public PositionUpdatingStrategy(int pictureWidth, int pictureHeight) {
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
    }

    public void updatePosition(Pointer pointer) {
        double distanceToBoundary = getDistanceToBoundary(pointer);
        pointer.getVelocity().rotate(Math.PI / (distanceToBoundary * distanceToBoundary + 1));
        pointer.move();

    }

    private double getDistanceToBoundary(Pointer pointer) {
        double toVertical = distanceToLine(pointer.getX(), pointer.getVelocity().getX(), pictureWidth);
        double toHorizontal = distanceToLine(pointer.getY(), pointer.getVelocity().getY(), pictureHeight);
        return Math.min(toHorizontal, toVertical);
    }

    private double distanceToLine(double coordinate, double velocity, double limit) {
        if (velocity < EPS) {
            if (velocity > -EPS) {
                return Double.POSITIVE_INFINITY;
            } else {
                return -coordinate / velocity;
            }
        } else {
            return (limit - coordinate) / velocity;
        }
    }
}
