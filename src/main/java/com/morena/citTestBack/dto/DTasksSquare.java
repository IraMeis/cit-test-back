package com.morena.citTestBack.dto;

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

    private List<Long> outputMatrix;

    public boolean isCorrect() {
        return getInputMatrix() != null && getInputMatrix().size() == ConstantUtil.getLineMatrixSize();
    }

    public boolean isCorrectCodeCheck() {
        return getTypeCode() != null &&
                Objects.equals(getTypeCode(), TaskTypeEnum.taskSquare.getCode()) &&
                isCorrect();
    }
}
