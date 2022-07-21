package com.morena.citTestBack.dto;

import com.morena.citTestBack.enums.TaskTypeEnum;
import lombok.*;

import java.util.List;
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

}
