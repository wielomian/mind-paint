package com.github.wielomian.tgc_mock.controller;

/**
 * Created by Jan Tulowiecki on 2018-08-15.
 */
public class SenderServiceRunnable implements Runnable {

    //TODO: properties
    private static final int SEND_PERIOD_MS = 780;

    private final SenderService senderService;
    private long lastSendTime = 0;

    public SenderServiceRunnable(SenderService senderService){
        this.senderService = senderService;
    }

    @Override
    public void run() {
        while(true) {
            senderService.receiveAll();
            long now = System.currentTimeMillis();
            if (lastSendTime + SEND_PERIOD_MS < now) {
                lastSendTime = now;
                senderService.sendNewMeasurementToActive();
            }
        }
    }
}
