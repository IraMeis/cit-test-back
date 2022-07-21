package com.morena.citTestBack.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

/**
 * Class for elements of substring task (array entries)
 */
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

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name="task_ref", referencedColumnName = "unique_id")
    private TasksSubstring task;
}
