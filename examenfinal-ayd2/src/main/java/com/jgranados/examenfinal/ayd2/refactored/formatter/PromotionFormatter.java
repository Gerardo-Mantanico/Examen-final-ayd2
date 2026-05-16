package com.jgranados.examenfinal.ayd2.refactored.formatter;

public class PromotionFormatter implements MessageFormatter {

    @Override
    public String format(String message) {
        return "[PROMO] " + message + " - Limited offer!";
    }
}