package com.github.wielomian.mind_paint.engine;

import com.github.wielomian.mind_paint.connector.DataStream;
import com.github.wielomian.mind_paint.connector.Measurement;
import com.github.wielomian.mind_paint.model.DataAccessObject;
import com.github.wielomian.mind_paint.model.PictureSetup;
import com.github.wielomian.mind_paint.model.Vector2D;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

import java.util.Optional;

/**
 * Created by Jan Tulowiecki on 2018-04-27.
 */
public class Timer extends AnimationTimer {

    private final PictureSetup pictureSetup = DataAccessObject.getInstance().getPictureSetup();
    private final DataStream dataStream = DataAccessObject.getInstance().getDataStream();
    private long previousTimestamp = 0;
    private final PictureSetupUpdater pictureSetupUpdater = new PictureSetupUpdaterImpl();
    private final Runnable refreshTask;

    public Timer(Runnable refreshTask) {
        this.refreshTask = refreshTask;
    }

    @Override
    public void handle(long now) {
        if (previousTimestamp + 40_000_000L < now) {
            Optional<Measurement> measurement = dataStream.getMeasurement();
            if (measurement.isPresent()) {
                previousTimestamp = now;
                pictureSetupUpdater.update(pictureSetup, measurement.get());
                refreshTask.run();
            }
        }
    }
}
