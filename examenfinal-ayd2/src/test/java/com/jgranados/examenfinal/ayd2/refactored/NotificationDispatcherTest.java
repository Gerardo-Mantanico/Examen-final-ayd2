package com.jgranados.examenfinal.ayd2.refactored;

import com.jgranados.examenfinal.ayd2.refactored.model.Notification;
import com.jgranados.examenfinal.ayd2.refactored.sender.NotificationSender;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class NotificationDispatcherTest {

    @Test
    void dispatchCallsEverySender() {
        NotificationSender sender1 = mock(NotificationSender.class);
        NotificationSender sender2 = mock(NotificationSender.class);
        NotificationDispatcher dispatcher = new NotificationDispatcher(List.of(sender1, sender2));
        Notification notification = Notification.builder()
                .userId("user123")
                .message("Hola")
                .build();

        dispatcher.dispatch(notification);

        verify(sender1).send(notification);
        verify(sender2).send(notification);
    }

    @Test
    void dispatchNullThrowsException() {
        NotificationDispatcher dispatcher = new NotificationDispatcher(new ArrayList<>());

        assertThrows(IllegalArgumentException.class, () -> dispatcher.dispatch(null));
    }

    @Test
    void addSenderAddsChannelForNextDispatch() {
        NotificationSender sender1 = mock(NotificationSender.class);
        NotificationSender sender2 = mock(NotificationSender.class);
        NotificationDispatcher dispatcher = new NotificationDispatcher(new ArrayList<>(List.of(sender1)));
        Notification notification = Notification.builder()
                .userId("user123")
                .message("Hola")
                .build();

        dispatcher.addSender(sender2);
        dispatcher.dispatch(notification);

        verify(sender1, times(1)).send(notification);
        verify(sender2, times(1)).send(notification);
        verifyNoMoreInteractions(sender1, sender2);
    }
}