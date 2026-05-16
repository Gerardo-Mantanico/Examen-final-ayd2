package com.jgranados.examenfinal.ayd2.refactored.model;


public final class Notification {

    private final String userId;
    private final String message;
    private final String type;
    private final String priority;

    private Notification(Builder builder) {
        this.userId = builder.userId;
        this.message = builder.message;
        this.type = builder.type;
        this.priority = builder.priority;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public String getPriority() {
        return priority;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String userId;
        private String message;
        private String type = "general";
        private String priority = "normal";

        private Builder() {
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder priority(String priority) {
            this.priority = priority;
            return this;
        }

        public Notification build() {
            if (userId == null || userId.trim().isEmpty()) {
                throw new IllegalArgumentException("userId is required");
            }
            if (message == null || message.trim().isEmpty()) {
                throw new IllegalArgumentException("message is required");
            }
            return new Notification(this);
        }
    }
}