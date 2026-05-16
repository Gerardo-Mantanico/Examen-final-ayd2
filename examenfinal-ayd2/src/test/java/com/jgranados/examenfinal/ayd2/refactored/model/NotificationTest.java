package com.jgranados.examenfinal.ayd2.refactored.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NotificationTest {

    @Test
    void builderCreatesObjectWithAllFields() {
        Notification notification = Notification.builder()
                .userId("user123")
                .message("Hola")
                .type("alert")
                .priority("high")
                .build();

        assertEquals("user123", notification.getUserId());
        assertEquals("Hola", notification.getMessage());
        assertEquals("alert", notification.getType());
        assertEquals("high", notification.getPriority());
    }

    @Test
    void defaultTypeIsGeneral() {
        Notification notification = Notification.builder()
                .userId("user123")
                .message("Hola")
                .build();

        assertEquals("general", notification.getType());
    }

    @Test
    void defaultPriorityIsNormal() {
        Notification notification = Notification.builder()
                .userId("user123")
                .message("Hola")
                .build();

        assertEquals("normal", notification.getPriority());
    }

    @Test
    void missingUserIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Notification.builder()
                .message("Hola")
                .build());
    }

    @Test
    void missingMessageThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Notification.builder()
                .userId("user123")
                .build());
    }

    @Test
    void blankMessageThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Notification.builder()
                .userId("user123")
                .message("   ")
                .build());
    }
}