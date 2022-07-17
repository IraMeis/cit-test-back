package com.morena.citTestBack.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "task_substring_elements", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TaskSubstringElements extends BaseModelEntity {

    @Column(name = "string_array_entry")
    private String stringArrayEntry;

    @ManyToOne
    @JoinColumn(name="element_type", referencedColumnName = "code")
    private DictTaskSubstringElementTypes elementType;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name="task_ref", referencedColumnName = "unique_id")
    private TasksSubstring task;
}
