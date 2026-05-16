package com.jgranados.examenfinal.ayd2.refactored.sender;

import com.jgranados.examenfinal.ayd2.legacy.external.ThirdPartySMSProvider;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SMSAdapterTest {

    @Mock
    private ThirdPartySMSProvider smsProvider;

    private SMSAdapter adapter;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        adapter = new SMSAdapter(smsProvider);
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void user123HasPhoneAndDispatchesSms() {
        Notification notification = Notification.builder()
                .userId("user123")
                .message("Mensaje corto")
                .build();

        adapter.send(notification);

        verify(smsProvider).dispatchSMS("5551234567", "Mensaje corto");
    }

    @Test
    void user789WithoutPhoneDoesNotDispatchAndPrintsMessage() {
        Notification notification = Notification.builder()
                .userId("user789")
                .message("Mensaje corto")
                .build();

        adapter.send(notification);

        verify(smsProvider, never()).dispatchSMS(anyString(), anyString());
        assertTrue(outContent.toString().contains("No phone registered"));
    }

    @Test
    void longMessageIsTruncatedTo160Characters() {
        String longMessage = "a".repeat(200);
        Notification notification = Notification.builder()
                .userId("user123")
                .message(longMessage)
                .build();

        adapter.send(notification);

        verify(smsProvider).dispatchSMS("5551234567", longMessage.substring(0, 160));
    }
}