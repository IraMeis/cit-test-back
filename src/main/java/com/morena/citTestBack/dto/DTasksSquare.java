package com.morena.citTestBack.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DTasksSquare implements DTask {

    private final long typeCode = 2;

    private Long id;
    private UUID uuid;
    private String createdTimestamp;
    private String modifiedTimestamp;

    private List<Long> inputMatrix;

}
