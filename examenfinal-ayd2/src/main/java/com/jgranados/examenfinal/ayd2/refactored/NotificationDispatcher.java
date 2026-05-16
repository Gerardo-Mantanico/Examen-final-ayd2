package com.jgranados.examenfinal.ayd2.refactored;

import com.jgranados.examenfinal.ayd2.refactored.model.Notification;
import com.jgranados.examenfinal.ayd2.refactored.sender.NotificationSender;

import java.util.ArrayList;
import java.util.List;

public class NotificationDispatcher {

    private final List<NotificationSender> senders;

    public NotificationDispatcher(List<NotificationSender> senders) {
        this.senders = new ArrayList<>(senders);
    }

    public void dispatch(Notification notification) {
        if (notification == null) {
            throw new IllegalArgumentException("notification is required");
        }

        for (NotificationSender sender : senders) {
            sender.send(notification);
        }
    }

    public void addSender(NotificationSender sender) {
        senders.add(sender);
    }
}