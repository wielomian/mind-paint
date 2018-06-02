package com.github.wielomian.mind_paint.controller;

import com.github.wielomian.mind_paint.model.Pointer;
import javafx.beans.value.ChangeListener;

import java.util.function.BiConsumer;

/**
 * Created by Jan Tulowiecki on 2018-06-02.
 */
public class SliderListener {

    private Pointer activePointer;

    ChangeListener<? super Number> createChangeListener(BiConsumer<Pointer, Number> consumer) {
        return (ign1, ign2, newValue) -> {
            if (activePointer != null) {
                consumer.accept(activePointer, newValue);
            }
        };
    }

    public void setActivePointer(Pointer activePointer) {
        this.activePointer = activePointer;
    }
}
