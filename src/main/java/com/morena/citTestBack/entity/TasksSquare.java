package com.morena.citTestBack.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tasks_square", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TasksSquare extends BaseModelEntity {

    @OneToMany(mappedBy="task", fetch = FetchType.LAZY)
    private Set<TaskSquareElements> elements;
}
