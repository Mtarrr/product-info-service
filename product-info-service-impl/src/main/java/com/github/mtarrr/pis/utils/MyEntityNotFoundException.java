package com.github.mtarrr.pis.utils;

public class MyEntityNotFoundException extends RuntimeException {
    public MyEntityNotFoundException(String exception, String id) {
        super("id: " + id + ", exception: " + exception);
    }
}
