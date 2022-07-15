package com.morena.citTestBack.repository;

import com.morena.citTestBack.entity.BaseModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseModelEntityRepository<T extends BaseModelEntity, ID> extends JpaRepository<T, ID> {

    List<T> findAllByIsDeletedFalse();

    List<T> findAllByIsDeletedTrue();

    Optional<T> findByUniqueIdAndIsDeletedFalse(Long id);

    Optional<T> findByUniqueId(Long id);

    Optional<T> findByUuidAndIsDeletedFalse(UUID uuid);

    Optional<T> findByUuid(UUID uuid);

    @Override
    @Modifying
    @Query("update #{#entityName} en set en.isDeleted=true where en.uniqueId = :id")
    void deleteById(ID id);

    @Override
    @Modifying
    @Query("update #{#entityName} en set en.isDeleted=true where en = :entity")
    void delete(T entity);
}
