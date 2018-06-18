package com.github.wielomian.mind_paint.controller;

import com.github.wielomian.mind_paint.configuration.MeasurementType;
import com.github.wielomian.mind_paint.model.DataAccessObject;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

/**
 * Created by Jan Tulowiecki on 2018-06-18.
 */
public class ConfigController {

    @FXML
    ComboBox<MeasurementType> brightnessCombo;

    public void initialize(){
        brightnessCombo.getItems().addAll(MeasurementType.values());
        brightnessCombo.setOnAction(ignored -> DataAccessObject.getInstance().getConfiguration().setBrightness(brightnessCombo.getValue()));
    }
}
