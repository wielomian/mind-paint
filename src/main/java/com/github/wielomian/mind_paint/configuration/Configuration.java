package com.github.wielomian.mind_paint.configuration;

/**
 * Created by Jan Tulowiecki on 2018-06-17.
 */
public class Configuration {

    private MeasurementType velocity = MeasurementType.BETA;
    private MeasurementType hue = MeasurementType.ALPHA;
    private MeasurementType saturation = MeasurementType.BETA;
    private MeasurementType brightness = MeasurementType.THETA;

    public MeasurementType getVelocity() {
        return velocity;
    }

    public void setVelocity(MeasurementType velocity) {
        this.velocity = velocity;
    }

    public MeasurementType getHue() {
        return hue;
    }

    public void setHue(MeasurementType hue) {
        this.hue = hue;
    }

    public MeasurementType getSaturation() {
        return saturation;
    }

    public void setSaturation(MeasurementType saturation) {
        this.saturation = saturation;
    }

    public MeasurementType getBrightness() {
        return brightness;
    }

    public void setBrightness(MeasurementType brightness) {
        this.brightness = brightness;
    }
}
