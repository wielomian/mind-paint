package com.github.wielomian.mind_paint.engine;

import com.github.wielomian.mind_paint.connector.Measurement;
import com.github.wielomian.mind_paint.connector.ThingGearDataStream;
import com.github.wielomian.mind_paint.model.DataAccessObject;
import com.github.wielomian.mind_paint.model.PictureSetup;
import javafx.animation.AnimationTimer;

import java.util.Optional;

/**
 * Created by Jan Tulowiecki on 2018-04-27.
 */
public class Timer extends AnimationTimer {

    private final PictureSetup pictureSetup = DataAccessObject.getInstance().getPictureSetup();
    private final ThingGearDataStream dataStream = DataAccessObject.getInstance().getDataStream();
    private long previousTimestamp = 0;
    private final PictureSetupUpdater pictureSetupUpdater = new PictureSetupUpdater();
    private final Runnable refreshTask;

    public Timer(Runnable refreshTask) {
        this.refreshTask = refreshTask;
    }

    @Override
    public void handle(long now) {
        //TODO: move to properties
        if (previousTimestamp + 42_000_000L < now) {
            Optional<Measurement> measurement = dataStream.getMeasurement();
            if (measurement.isPresent()) {
                previousTimestamp = now;
                pictureSetupUpdater.update(pictureSetup, measurement.get());
                refreshTask.run();
            }
        }
    }
}
