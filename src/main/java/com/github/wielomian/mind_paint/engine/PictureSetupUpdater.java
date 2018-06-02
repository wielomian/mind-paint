package com.github.wielomian.mind_paint.engine;

import com.github.wielomian.mind_paint.connector.Measurement;
import com.github.wielomian.mind_paint.model.PictureSetup;

/**
 * Created by Jan Tulowiecki on 2018-05-20.
 */
public interface PictureSetupUpdater {
    void update(PictureSetup pictureSetup, Measurement measurement);
}
