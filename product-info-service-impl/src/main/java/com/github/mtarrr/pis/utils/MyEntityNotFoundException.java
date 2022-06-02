package com.github.mtarrr.pis.utils;

public class MyEntityNotFoundException extends RuntimeException {
    public MyEntityNotFoundException(String id) {
        super(", id: " + id);
    }
}
