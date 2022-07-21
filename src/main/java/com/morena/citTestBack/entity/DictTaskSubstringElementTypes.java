package com.morena.citTestBack.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class for dict values of types of elements of substring task (array1 or array2)
 */
@Entity
@Table(name = "dict_task_substring_element_types", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DictTaskSubstringElementTypes extends BaseModelDictionaryEntity {
}
