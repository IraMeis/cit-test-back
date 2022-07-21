package com.morena.citTestBack.dto;

import com.morena.citTestBack.enums.TaskTypeEnum;
import lombok.*;

import java.util.List;
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

}
