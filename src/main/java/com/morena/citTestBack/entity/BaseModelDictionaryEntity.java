package com.morena.citTestBack.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Parent class for dictionary entities
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class BaseModelDictionaryEntity extends BaseModelEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private Long code;

    @Column(name = "description")
    private String description;

}
