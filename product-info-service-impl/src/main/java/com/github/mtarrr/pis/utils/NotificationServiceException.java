package com.github.mtarrr.pis.utils;

public class NotificationServiceException extends RuntimeException {
    public NotificationServiceException(String message, String exception) {
        super(message + ", exception: " + exception);
    }
}
