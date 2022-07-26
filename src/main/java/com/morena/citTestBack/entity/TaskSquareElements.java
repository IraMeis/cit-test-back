package com.morena.citTestBack.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

/**
 * Class for elements of square task (matrix entries)
 */
@Entity
@Table(name = "task_square_elements", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TaskSquareElements extends BaseModelEntity {

    @Column(name = "matrix_entry")
    private Long matrixEntry;

    @Column(name = "order_in_list")
    private Long order;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name="task_ref", referencedColumnName = "unique_id")
    private TasksSquare task;
}
