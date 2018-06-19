package com.github.wielomian.mind_paint.engine;

import com.github.wielomian.mind_paint.configuration.Configuration;
import com.github.wielomian.mind_paint.connector.Measurement;
import com.github.wielomian.mind_paint.model.DataAccessObject;
import com.github.wielomian.mind_paint.model.PictureSetup;
import com.github.wielomian.mind_paint.model.Pointer;
import javafx.scene.paint.Color;

/**
 * Created by Jan Tulowiecki on 2018-05-20.
 */
public class PictureSetupUpdater {

    private int steps = 0;

    public void update(PictureSetup pictureSetup, Measurement measurement) {
        steps++;
        PositionUpdatingStrategy positionUpdatingStrategy = pictureSetup.getPositionUpdatingStrategy();
        Configuration configuration = DataAccessObject.getInstance().getConfiguration();
        for (Pointer pointer : pictureSetup.getPointers()) {
            if (steps % 5 == 0) {

                executeVelocityChange(pointer, measurement.getForType(configuration.getVelocity()));
                executeColorChange(
                        pointer,
                        measurement.getForType(configuration.getBrightness()),
                        measurement.getForType(configuration.getSaturation()),
                        measurement.getForType(configuration.getHue())
                );
            }
            if (steps % 7 == 0 && pointer.getRadius() < 9) {
                pointer.setRadius(pointer.getRadius() + 0.5);
            }
            positionUpdatingStrategy.updatePosition(pointer);
        }
    }

    private void executeVelocityChange(Pointer pointer, double value) {
        pointer.getVelocity().rotate(value - 0.5);
    }

    private void executeColorChange(Pointer pointer, double brightnessValue, double saturationValue, double hueValue) {

        Color color = pointer.getColor();
        double hue = color.getHue();
        double saturation = color.getSaturation();
        double brightness = color.getBrightness();

        double newHue = strictBounds(0, 360.0, hue + 10.0 * (hueValue + pointer.getHueSensitivity()));
        double newSaturation = strictBounds(0, 1, saturation + 0.03 * (saturationValue + pointer.getSaturationSensitivity()));
        double newBrightness = strictBounds(0, 1, brightness + 0.03 * (brightnessValue + pointer.getBrightnessSensitivity()));
        pointer.setColor(Color.hsb(newHue, newSaturation, newBrightness));
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
