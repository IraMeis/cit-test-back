package com.morena.citTestBack.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.morena.citTestBack.enums.TaskTypeEnum;
import com.morena.citTestBack.util.ConstantUtil;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * POJO TasksSquare
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DTasksSquare implements DTask {

    private final Long typeCode = TaskTypeEnum.taskSquare.getCode();

    private Long id;
    private UUID uuid;
    private String createdTimestamp;
    private String modifiedTimestamp;

    private List<Long> inputMatrix;

    private Long cost;
    private List<Long> outputMatrix;

    @JsonIgnore
    public boolean isCorrect() {
        return getInputMatrix() != null && getInputMatrix().size() == ConstantUtil.getLineMatrixSize() &&
                getInputMatrix()
                        .stream()
                        .distinct()
                        .filter(elem -> elem < 10L && elem > 0L)
                        .count() == ConstantUtil.getLineMatrixSize();
    }

    @JsonIgnore
    public boolean isCorrectCodeCheck() {
        return getTypeCode() != null &&
                Objects.equals(getTypeCode(), TaskTypeEnum.taskSquare.getCode()) &&
                isCorrect();
    }
}
