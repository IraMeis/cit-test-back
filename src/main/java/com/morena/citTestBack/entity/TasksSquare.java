package com.morena.citTestBack.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Class for substring task
 */
@Entity
@Table(name = "tasks_square", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TasksSquare extends BaseModelEntity {

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="task", fetch = FetchType.LAZY)
    private Set<TaskSquareElements> elements;
}
