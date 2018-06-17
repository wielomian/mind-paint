package com.github.wielomian.mind_paint.controller;

import com.github.wielomian.mind_paint.engine.Timer;
import com.github.wielomian.mind_paint.model.DataAccessObject;
import com.github.wielomian.mind_paint.model.Pointer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Controller {

    @FXML
    private Button paintOnOff;
    @FXML
    private Button pointersOnOff;

    @FXML
    private Pane picturePane;

    @FXML
    private Canvas canvas;

    @FXML
    private Slider brightnessSlider;

    @FXML
    private Slider hueSlider;

    @FXML
    private Slider saturationSlider;

    @FXML
    private Label pointerName;

    private boolean isRunning = false;
    private boolean arePointersVisible = false;
    private Timer paintTimer = new Timer(this::onRefresh);
    private SliderListener sliderListener = new SliderListener();

    public void onRefresh() {
        for (Pointer pointer : DataAccessObject.getInstance().getPictureSetup().getPointers()) {
            if (pointer.isTraceEnabled()) {
                GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
                graphicsContext.setFill(pointer.getColor());
                double radius = pointer.getRadius();
                graphicsContext.fillOval(pointer.getX() - radius, pointer.getY() - radius, 2 * radius, 2 * radius);
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

    public void onClearButtonCLicked() {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.setFill(Color.WHITE);
        graphicsContext2D.fillRect(1, 1, canvas.getWidth() - 2, canvas.getHeight() - 2);
    }

    public void onShowHidePointersClicked() {
        arePointersVisible = !arePointersVisible;
        List<Pointer> pointers = DataAccessObject.getInstance().getPictureSetup().getPointers();
        List<Circle> sprities = pointers
                .stream()
                .map(Pointer::getSprite)
                .collect(Collectors.toList());
        if (arePointersVisible) {
            for (Pointer pointer : pointers) {
                pointer.getSprite().setOnMouseClicked(event -> {
                    sliderListener.setActivePointer(pointer);
                    brightnessSlider.setValue(pointer.getBrightnessSensitivity());
                    hueSlider.setValue(pointer.getHueSensitivity());
                    saturationSlider.setValue(pointer.getSaturationSensitivity());
                    pointerName.setText("Pointer " + pointer.getId());
                });
            }
            picturePane.getChildren().addAll(sprities);
            pointersOnOff.setText("Hide pointers");
        } else {
            picturePane.getChildren().removeAll(sprities);
            pointersOnOff.setText("Show pointers");
        }
    }

    public void initialize() {
        setSlider(brightnessSlider, (p, d) -> p.setBrightnessSensitivity(d.doubleValue()));
        setSlider(hueSlider, (p, d) -> p.setHueSensitivity(d.doubleValue()));
        setSlider(saturationSlider, (p, d) -> p.setSaturationSensitivity(d.doubleValue()));
    }

    private void setSlider(Slider slider, BiConsumer<Pointer, Number> changeListener){
        slider.setMin(-1.0);
        slider.setMax(0.0);
        slider.setValue(-0.5);
        slider.valueProperty().addListener(sliderListener.createChangeListener(changeListener));
    }
}
