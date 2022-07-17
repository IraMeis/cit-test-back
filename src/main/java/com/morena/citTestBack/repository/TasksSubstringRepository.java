package com.morena.citTestBack.repository;

import com.morena.citTestBack.entity.TasksSubstring;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksSubstringRepository extends BaseModelEntityRepository<TasksSubstring, Long>, QuerydslPredicateExecutor<TasksSubstring> {
}
