package com.morena.citTestBack.repository;

import com.morena.citTestBack.entity.TasksSquare;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksSquareRepository extends BaseModelEntityRepository<TasksSquare, Long>, QuerydslPredicateExecutor<TasksSquare> {
}
