package com.github.wielomian.mind_paint.connector;

/**
 * Created by Jan Tulowiecki on 2018-08-15.
 */
class Average {

    private long total = 0;
    private int count = 0;

    public void add(int value){
        total += value;
        count++;
    }

    public double get() {
        return (double) total / count;
    }
}
