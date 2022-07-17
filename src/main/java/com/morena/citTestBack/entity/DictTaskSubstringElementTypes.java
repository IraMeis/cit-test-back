package com.morena.citTestBack.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dict_task_substring_element_types", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DictTaskSubstringElementTypes extends BaseModelDictionaryEntity {
}
