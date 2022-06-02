package com.github.mtarrr.pis.utils;

public enum ErrorCode {
    UNEXPECTED_ERROR("Unexpected error", "0001"),
    NO_HANDLER_FOUND("No Handler Found", "0002"),
    MALFORMED_JSON_REQUEST("Malformed JSON Request", "0003"),
    ELASTIC_SERVICE_ERROR("Elastic service error", "0004"),
    NOTIFICATION_SERVICE_ERROR("Notification service error", "0005"),
    ENTITY_NOT_FOUND("Entity not found", "0006");

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    private String message;
    private String code;

    ErrorCode(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
