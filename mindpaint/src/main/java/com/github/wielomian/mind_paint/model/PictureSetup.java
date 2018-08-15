package com.github.wielomian.mind_paint.model;

import com.github.wielomian.mind_paint.engine.PositionUpdatingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan Tulowiecki on 2018-05-20.
 */
public class PictureSetup {
    private final List<Pointer> pointers;
    private final int width;
    private final int height;
    private final PositionUpdatingStrategy positionUpdatingStrategy;

    public PictureSetup(int width, int height) {
        this.width = width;
        this.height = height;
        this.pointers = new ArrayList<>();
        positionUpdatingStrategy = new PositionUpdatingStrategy(width, height);
    }

    public List<Pointer> getPointers() {
        return pointers;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public PositionUpdatingStrategy getPositionUpdatingStrategy() {
        return positionUpdatingStrategy;
    }

}
