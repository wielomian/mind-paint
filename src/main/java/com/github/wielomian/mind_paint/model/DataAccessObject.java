package com.github.wielomian.mind_paint.model;

/**
 * Created by Jan Tulowiecki on 2018-04-27.
 */
public class DataAccessObject {

    private final PictureSetup pictureSetup;

    private DataAccessObject() {
        pictureSetup = new PictureSetup(600, 376);
        pictureSetup.getPointers().add(new Pointer(10, 300, 180, -2, 1));
        pictureSetup.getPointers().add(new Pointer(10, 290, 180, 2, -2));
        pictureSetup.getPointers().add(new Pointer(10, 290, 190, 3, -2));
        pictureSetup.getPointers().add(new Pointer(10, 300, 190, -2, 0));
    }

    private static DataAccessObject instance = new DataAccessObject();

    public static DataAccessObject getInstance() {
        return instance;
    }

    public PictureSetup getPictureSetup() {
        return pictureSetup;
    }
}
