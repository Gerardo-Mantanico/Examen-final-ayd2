package com.jgranados.examenfinal.ayd2.refactored.sender;

import com.jgranados.examenfinal.ayd2.legacy.AuditLogger;
import com.jgranados.examenfinal.ayd2.legacy.external.ThirdPartySMSProvider;
import com.jgranados.examenfinal.ayd2.legacy.external.UserPhoneRepository;
import com.jgranados.examenfinal.ayd2.refactored.model.Notification;


public class SMSAdapter implements NotificationSender {

    private final ThirdPartySMSProvider smsProvider;

    public SMSAdapter(ThirdPartySMSProvider smsProvider) {
        this.smsProvider = smsProvider;
    }

    @Override
    public void send(Notification notification) {
        String phoneNumber = UserPhoneRepository.getPhoneNumber(notification.getUserId());
        if (phoneNumber == null) {
            AuditLogger.log("No phone registered for user " + notification.getUserId());
            System.out.println("No phone registered");
            return;
        }

        String content = notification.getMessage();
        if (content.length() > 160) {
            content = content.substring(0, 160);
        }

        smsProvider.dispatchSMS(phoneNumber, content);
    }
}