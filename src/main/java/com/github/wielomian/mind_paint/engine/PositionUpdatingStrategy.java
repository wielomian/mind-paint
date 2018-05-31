package com.github.wielomian.mind_paint.engine;

import com.github.wielomian.mind_paint.model.Pointer;

/**
 * Created by Jan Tulowiecki on 2018-05-20.
 */
public interface PositionUpdatingStrategy {

    void updatePosition(Pointer pointer);
}
