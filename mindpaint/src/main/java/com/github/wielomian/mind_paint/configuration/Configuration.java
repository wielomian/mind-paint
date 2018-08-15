package com.github.wielomian.mind_paint.configuration;

/**
 * Created by Jan Tulowiecki on 2018-06-17.
 */
public class Configuration {

    private MeasurementType velocity = MeasurementType.HIGH_ALPHA;
    private MeasurementType hue = MeasurementType.LOW_ALPHA;
    private MeasurementType saturation = MeasurementType.HIGH_ALPHA;
    private MeasurementType brightness = MeasurementType.LOW_BETA;

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
