package com.github.wielomian.mind_paint.engine;

import com.github.wielomian.mind_paint.connector.Measurement;
import com.github.wielomian.mind_paint.model.PictureSetup;
import com.github.wielomian.mind_paint.model.Pointer;
import javafx.scene.paint.Color;

/**
 * Created by Jan Tulowiecki on 2018-05-20.
 */
public class PictureSetupUpdaterImpl implements PictureSetupUpdater {

    private int steps = 0;

    @Override
    public void update(PictureSetup pictureSetup, Measurement measurement) {
        steps++;
        PositionUpdatingStrategy positionUpdatingStrategy = pictureSetup.getPositionUpdatingStrategy();
        for (Pointer pointer : pictureSetup.getPointers()) {
            if (steps % 5 == 0) {
                pointer.getVelocity().rotate(measurement.getBeta() - 0.5);

                Color color = pointer.getColor();
                double hue = color.getHue();
                double saturation = color.getSaturation();
                double brightness = color.getBrightness();

                double newHue = hue;
                double newSaturation = saturation;
                double newBrightness = strictBounds(0, 1, brightness + 0.03 * (measurement.getAlpha() + pointer.getBrightnessSensitivity()));
                pointer.setColor(Color.hsb(newHue, newSaturation, newBrightness));
            }
            if (steps % 7 == 0 && pointer.getRadius() < 9) {
                pointer.setRadius(pointer.getRadius() + 0.5);
            }
            positionUpdatingStrategy.updatePosition(pointer);
        }
    }

    private static double strictBounds(double lower, double upper, double value) {
        if (value < lower) {
            return lower;
        } else if (value > upper) {
            return upper;
        } else {
            return value;
        }
    }
}
