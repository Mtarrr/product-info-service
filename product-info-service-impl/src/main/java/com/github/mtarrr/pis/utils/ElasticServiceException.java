package com.github.mtarrr.pis.utils;

public class ElasticServiceException extends RuntimeException {
    public ElasticServiceException(String message, String exception) {
        super(message + ", exception: " + exception);
    }
}
