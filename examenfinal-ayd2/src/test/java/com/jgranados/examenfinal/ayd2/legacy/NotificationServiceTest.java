package com.jgranados.examenfinal.ayd2.legacy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


class NotificationServiceTest {

    private NotificationService service;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        service = new NotificationService();
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testAlertFormatContienePrefijoCorrecto() {
        service.sendNotification("user1", "Servidor caido", "alert");
        String output = outContent.toString();

        assertTrue(output.contains("[ALERT]"), "Debe contener el prefijo [ALERT]");
        assertTrue(output.contains("Action required!"), "Debe contener 'Action required!'");
    }

    @Test
    void testReminderFormatContienePrefijoCorrecto() {
        service.sendNotification("user1", "Reunion a las 5pm", "reminder");
        String output = outContent.toString();

        assertTrue(output.contains("[REMINDER]"), "Debe contener el prefijo [REMINDER]");
        assertTrue(output.contains("Don't forget!"), "Debe contener 'Don't forget!'");
    }

    @Test
    void testPromotionFormatContienePrefijoCorrecto() {
        service.sendNotification("user1", "50% de descuento", "promotion");
        String output = outContent.toString();

        assertTrue(output.contains("[PROMO]"), "Debe contener el prefijo [PROMO]");
        assertTrue(output.contains("Limited offer!"), "Debe contener 'Limited offer!'");
    }

    @Test
    void testTipoDesconocidoNoAgregaPrefijoAlMensaje() {
        service.sendNotification("user1", "Hola mundo", "general");
        String output = outContent.toString();

        // con tipo desconocido el mensaje no debe tener prefijo especial
        assertTrue(output.contains("Hola mundo"));
        assertFalse(output.contains("[ALERT]"));
        assertFalse(output.contains("[REMINDER]"));
        assertFalse(output.contains("[PROMO]"));
    }

    @Test
    void testEmailEnviadoADireccionCorrecta() {
        service.sendNotification("user42", "mensaje de prueba", "alert");
        String output = outContent.toString();

        // el email debe ser userId@company.com
        assertTrue(output.contains("user42@company.com"), "El email debe ser userId@company.com");
    }

    @Test
    void testMensajeNullLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> service.sendNotification("user1", null, "alert"),
                "Mensaje null debe lanzar IllegalArgumentException");
    }

    @Test
    void testMensajeVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> service.sendNotification("user1", "   ", "alert"),
                "Mensaje vacio o solo espacios debe lanzar IllegalArgumentException");
    }
}
