package com.morena.citTestBack.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.morena.citTestBack.enums.TaskTypeEnum;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * POJO TasksSubstring
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DTasksSubstring implements DTask {

    private final Long typeCode = TaskTypeEnum.taskSubstring.getCode();

    private Long id;
    private UUID uuid;
    private String createdTimestamp;
    private String modifiedTimestamp;

    private List<String> array1;
    private List<String> array2;

    private List<String> arrayResult;

    @JsonIgnore
    public boolean isCorrect() {
        return getArray2() != null && !getArray2().isEmpty() &&
                getArray1() != null && !getArray1().isEmpty();
    }

    @JsonIgnore
    public boolean isCorrectCodeCheck() {
        return getTypeCode() != null &&
                Objects.equals(getTypeCode(), TaskTypeEnum.taskSubstring.getCode()) &&
                isCorrect();
    }
}
