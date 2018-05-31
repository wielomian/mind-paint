package com.github.wielomian.mind_paint.engine;

import com.github.wielomian.mind_paint.model.PictureSetup;
import com.github.wielomian.mind_paint.model.Pointer;
import javafx.scene.paint.Color;

/**
 * Created by Jan Tulowiecki on 2018-05-20.
 */
public class PictureSetupUpdaterImpl implements PictureSetupUpdater {

    private int steps = 0;

    @Override
    public void update(PictureSetup pictureSetup){
        steps++;
        PositionUpdatingStrategy positionUpdatingStrategy = pictureSetup.getPositionUpdatingStrategy();
        for (Pointer pointer : pictureSetup.getPointers()){
            if (steps % 5 == 0) {
                pointer.getVelocity().rotate(Math.random() - 0.5);

                Color color = pointer.getColor();
                int newRed = (int) ((Math.random() - 0.42) * 12 + color.getRed() * 255);
                int newGreen = (int) ((Math.random() - 0.42) * 12 + color.getGreen() * 255);
                int newBlue = (int) ((Math.random() - 0.42) * 12 + color.getBlue() * 255);
                newRed = Math.max(0, Math.min(newRed, 255));
                newBlue = Math.max(0, Math.min(newBlue, 255));
                newGreen = Math.max(0, Math.min(newGreen, 255));
                pointer.setColor(Color.rgb(newRed, newGreen, newBlue));
            }
            if (steps % 7 == 0 && pointer.getRadius() < 18) {
                pointer.setRadius(pointer.getRadius() + 1);
            }
            positionUpdatingStrategy.updatePosition(pointer);
        }
    }
}
