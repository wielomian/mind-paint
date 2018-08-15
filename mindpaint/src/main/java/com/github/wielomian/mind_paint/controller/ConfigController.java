package com.github.wielomian.mind_paint.controller;

import com.github.wielomian.mind_paint.configuration.Configuration;
import com.github.wielomian.mind_paint.configuration.MeasurementType;
import com.github.wielomian.mind_paint.model.DataAccessObject;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Created by Jan Tulowiecki on 2018-06-18.
 */
public class ConfigController {

    @FXML
    private ComboBox<MeasurementType> brightnessCombo;

    @FXML
    private ComboBox<MeasurementType> hueCombo;

    @FXML
    private ComboBox<MeasurementType> saturationCombo;

    @FXML
    private ComboBox<MeasurementType> velocityCombo;

    public void initialize() {
        addCombo(brightnessCombo, Configuration::setBrightness, Configuration::getBrightness);
        addCombo(hueCombo, Configuration::setHue, Configuration::getHue);
        addCombo(saturationCombo, Configuration::setSaturation, Configuration::getSaturation);
        addCombo(velocityCombo, Configuration::setVelocity, Configuration::getVelocity);
    }

    private void addCombo(ComboBox<MeasurementType> combo, BiConsumer<Configuration, MeasurementType> setter,
                          Function<Configuration, MeasurementType> getter) {
        Configuration configuration = DataAccessObject.getInstance().getConfiguration();
        combo.getItems().addAll(MeasurementType.values());
        combo.setOnAction(ignored -> setter.accept(configuration, combo.getValue()));
        combo.getSelectionModel().select(getter.apply(configuration));
    }

    public void onOkButtonClicked() {
        DataAccessObject.getInstance().getConfigurationWindow().hide();
    }
}
