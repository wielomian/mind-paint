package com.github.wielomian.mind_paint;

import com.github.wielomian.mind_paint.engine.Timer;
import com.github.wielomian.mind_paint.model.DataAccessObject;
import com.github.wielomian.mind_paint.model.Pointer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Controller {

    @FXML
    private Button paintOnOff;

    private boolean isRunning = false;
    private Timer paintTimer = new Timer(this::onRefresh);

    @FXML
    private Canvas canvas;

    public void onRefresh() {
        for (Pointer pointer : DataAccessObject.getInstance().getPictureSetup().getPointers()) {
            if (pointer.isTraceEnabled()) {
                GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
                graphicsContext.setFill(pointer.getColor());
                graphicsContext.fillOval(pointer.getX(), pointer.getY(), pointer.getRadius(), pointer.getRadius());
            }
        }
    }

    public void onPaintOnOffClicked() {
        if (isRunning) {
            isRunning = false;
            paintTimer.stop();
            paintOnOff.setText("Start");
        } else {
            isRunning = true;
            paintTimer.start();
            paintOnOff.setText("Stop");
        }
    }

    public void onClearButtonCLicked(){
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.setFill(Color.WHITE);
        graphicsContext2D.fillRect(1, 1, canvas.getWidth() - 2, canvas.getHeight() - 2);
    }
}
