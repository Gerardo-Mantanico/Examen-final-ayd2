package com.jgranados.examenfinal.ayd2.refactored.formatter;

public class ReminderFormatter implements MessageFormatter {

    @Override
    public String format(String message) {
        return "[REMINDER] " + message + " - Don't forget!";
    }
}