package com.github.mtarrr.pis.model.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtils {

    public static ObjectMapper buildObjectMapper() {
        return new ObjectMapper();
    }
}
