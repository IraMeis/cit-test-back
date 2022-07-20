package com.morena.citTestBack.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.morena.citTestBack.dto.DTasksSquare;
import com.morena.citTestBack.dto.DTasksSubstring;

import java.util.Optional;


public class JsonStringToDTaskConvertor {

    private final static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JSR310Module());

    public static <T> Optional<T> convertToType (String json, Class<T> clazz) {
        try {
            return Optional.of(objectMapper.readValue(json, clazz));
        }
        catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

    public static Optional<DTasksSquare> convertToDTasksSquare (String json) {
        return convertToType(json, DTasksSquare.class);
    }

    public static Optional<DTasksSubstring> convertToDTasksSubstring (String json) {
        return convertToType(json, DTasksSubstring.class);
    }
}