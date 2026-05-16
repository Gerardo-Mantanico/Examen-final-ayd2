package com.jgranados.examenfinal.ayd2.refactored.formatter;

public class AlertFormatter implements MessageFormatter {

    @Override
    public String format(String message) {
        return "[ALERT] " + message + " - Action required!";
    }
}