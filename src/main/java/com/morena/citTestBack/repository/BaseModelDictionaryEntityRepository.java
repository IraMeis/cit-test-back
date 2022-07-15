package com.morena.citTestBack.repository;

import com.morena.citTestBack.entity.BaseModelDictionaryEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseModelDictionaryEntityRepository<T extends BaseModelDictionaryEntity,ID> extends BaseModelEntityRepository<T, ID> {

    Optional<T> findByCodeAndIsDeletedIsFalse(Long code);

    Optional<T> findByCode(Long code);

    Optional<T> findByNameAndIsDeletedIsFalse(String name);

    Optional<T> findByName(String name);
}
