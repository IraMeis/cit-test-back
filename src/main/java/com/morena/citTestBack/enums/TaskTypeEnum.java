package com.morena.citTestBack.enums;

import com.morena.citTestBack.dto.DTasksSquare;
import com.morena.citTestBack.dto.DTasksSubstring;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

/**
 * Enum with codes of DTasks
 */
@RequiredArgsConstructor
public enum TaskTypeEnum {

    taskSubstring(1L, DTasksSubstring.class),
    taskSquare(2L, DTasksSquare.class);

    @Getter
    private final Long code;

    @Getter
    private final Class<?> classType;

    public static Optional<Class<?>> getClassByCode (Long code) {
        for (var val: TaskTypeEnum.values()) {
            if(Objects.equals(val.code, code))
                return Optional.of(val.classType);
        }
        return Optional.empty();
    }
}
