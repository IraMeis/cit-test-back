package com.morena.citTestBack.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.morena.citTestBack.dto.DTasksSquare;
import com.morena.citTestBack.dto.DTasksSubstring;

import java.util.Optional;

/**
 * Class allows to convert String of json format into object and backwards
 *
 */
public class JsonStringDTaskConvertor {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Allows convert String of json format into object with given class T
     * @param json String of json format
     * @param clazz Class<T>
     * @param <T> object class
     * @return Optional<T>, empty if unable to parse String
     */
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

    /**
     * Allows convert object with given class T into String of json format
     * @param task object for converting
     * @param <T> object class
     * @return Optional<T>, empty if unable to parse String
     */
    public static<T> Optional<String> convertToJsonString (T task){
        try {
            return Optional.of(objectMapper.writeValueAsString(task));
        }
        catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }
}