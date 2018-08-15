package com.github.wielomian.mind_paint.export;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Jan Tulowiecki on 2018-06-19.
 */
public class CanvasExporter {

    public void exportCanvasToPngFile(Canvas canvas, File pngFile) {
        if (pngFile != null) {
            try {
                WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                canvas.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", pngFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
