package com.jgranados.examenfinal.ayd2.refactored.sender;

import com.jgranados.examenfinal.ayd2.legacy.external.PushNotificationService;
import com.jgranados.examenfinal.ayd2.refactored.model.Notification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PushAdapterTest {

    @Mock
    private PushNotificationService pushService;

    private PushAdapter adapter;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        adapter = new PushAdapter(pushService);
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void user123HasDeviceAndSendsPushWithDefaultPriority() {
        Notification notification = Notification.builder()
                .userId("user123")
                .message("Hola push")
                .type("alert")
                .build();

        adapter.send(notification);

        verify(pushService).sendPush("device_abc123", "alert", "Hola push", 0);
    }

    @Test
    void highPriorityIsMappedToOne() {
        Notification notification = Notification.builder()
                .userId("user123")
                .message("Alta prioridad")
                .type("promotion")
                .priority("high")
                .build();

        adapter.send(notification);

        verify(pushService).sendPush("device_abc123", "promotion", "Alta prioridad", 1);
    }

    @Test
    void userWithoutDeviceDoesNotSendAndPrintsMessage() {
        Notification notification = Notification.builder()
                .userId("user456")
                .message("Hola push")
                .build();

        adapter.send(notification);

        verify(pushService, never()).sendPush(anyString(), anyString(), anyString(), anyInt());
        assertTrue(outContent.toString().contains("No device registered"));
    }
}