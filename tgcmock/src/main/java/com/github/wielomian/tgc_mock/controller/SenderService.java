package com.github.wielomian.tgc_mock.controller;

import com.github.wielomian.tgc_mock.handler.MeasurementProducer;
import com.github.wielomian.tgc_mock.handler.SocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Jan Tulowiecki on 2018-08-15.
 */
public class SenderService {

    private final List<SocketHandler> socketHandlers = new CopyOnWriteArrayList<>();
    private final MeasurementProducer measurementProducer = new MeasurementProducer();

    public void receiveAll() {
        socketHandlers.forEach(SocketHandler::receiveAll);
    }

    public void sendNewMeasurementToActive() {
        String randomMeasurement = measurementProducer.getRandomMeasurement();
        socketHandlers.forEach(sh -> {
            if (sh.isSendEnabled()) {
                sh.send(randomMeasurement);
            }
        });
    }

    public void register(SocketHandler socketHandler) {
        socketHandlers.add(socketHandler);
    }
}
