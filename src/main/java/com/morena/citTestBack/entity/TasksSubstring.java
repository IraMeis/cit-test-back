package com.morena.citTestBack.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "tasks_substring", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TasksSubstring extends BaseModelEntity {

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="task", fetch = FetchType.LAZY)
    private Set<TaskSubstringElements> elements;
}
