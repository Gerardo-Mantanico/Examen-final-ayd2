package com.jgranados.examenfinal.ayd2.refactored.sender;

import com.jgranados.examenfinal.ayd2.legacy.AuditLogger;
import com.jgranados.examenfinal.ayd2.legacy.external.PushNotificationService;
import com.jgranados.examenfinal.ayd2.legacy.external.UserDeviceRepository;
import com.jgranados.examenfinal.ayd2.refactored.model.Notification;

public class PushAdapter implements NotificationSender {

    private final PushNotificationService pushService;

    public PushAdapter(PushNotificationService pushService) {
        this.pushService = pushService;
    }

    @Override
    public void send(Notification notification) {
        String deviceToken = UserDeviceRepository.getDeviceToken(notification.getUserId());
        if (deviceToken == null) {
            AuditLogger.log("No device registered for user " + notification.getUserId());
            System.out.println("No device registered");
            return;
        }

        int priority = "high".equals(notification.getPriority()) ? 1 : 0;
        pushService.sendPush(deviceToken, notification.getType(), notification.getMessage(), priority);
    }
}