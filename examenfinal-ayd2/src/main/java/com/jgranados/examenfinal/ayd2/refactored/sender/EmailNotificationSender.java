package com.jgranados.examenfinal.ayd2.refactored.sender;

import com.jgranados.examenfinal.ayd2.legacy.AuditLogger;
import com.jgranados.examenfinal.ayd2.legacy.EmailSender;
import com.jgranados.examenfinal.ayd2.refactored.formatter.AlertFormatter;
import com.jgranados.examenfinal.ayd2.refactored.formatter.DefaultFormatter;
import com.jgranados.examenfinal.ayd2.refactored.formatter.MessageFormatter;
import com.jgranados.examenfinal.ayd2.refactored.formatter.PromotionFormatter;
import com.jgranados.examenfinal.ayd2.refactored.formatter.ReminderFormatter;
import com.jgranados.examenfinal.ayd2.refactored.model.Notification;

public class EmailNotificationSender implements NotificationSender {

    @Override
    public void send(Notification notification) {
        MessageFormatter formatter = new DefaultFormatter();

        if ("alert".equals(notification.getType())) {
            formatter = new AlertFormatter();
        } else if ("reminder".equals(notification.getType())) {
            formatter = new ReminderFormatter();
        } else if ("promotion".equals(notification.getType())) {
            formatter = new PromotionFormatter();
        }

        String formattedMessage = formatter.format(notification.getMessage());
        String email = notification.getUserId() + "@company.com";

        EmailSender.send(email, formattedMessage);
        AuditLogger.log("Email sent to " + email + " with type: " + notification.getType());
    }
}