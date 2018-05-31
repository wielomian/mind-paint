package com.github.wielomian.mind_paint.engine;

import com.github.wielomian.mind_paint.model.DataAccessObject;
import com.github.wielomian.mind_paint.model.PictureSetup;
import com.github.wielomian.mind_paint.model.Vector2D;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

/**
 * Created by Jan Tulowiecki on 2018-04-27.
 */
public class Timer extends AnimationTimer {

    PictureSetup pictureSetup = DataAccessObject.getInstance().getPictureSetup();
    private long previousTimestamp = 0;
    private final PictureSetupUpdater pictureSetupUpdater = new PictureSetupUpdaterImpl();
    private final Runnable refreshTask;

    public Timer(Runnable refreshTask) {
        this.refreshTask = refreshTask;
    }

    private final Vector2D velocity = new Vector2D(2, 2);

    @Override
    public void handle(long now) {
        if (previousTimestamp + 40_000_000L < now) {
            previousTimestamp = now;
            pictureSetupUpdater.update(pictureSetup);
            refreshTask.run();
        }
    }
}
