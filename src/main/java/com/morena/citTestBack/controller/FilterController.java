package com.morena.citTestBack.controller;

import com.morena.citTestBack.entity.TasksSquare;
import com.morena.citTestBack.entity.TasksSubstring;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/filter")
@RequiredArgsConstructor
public class FilterController {

    @GetMapping()
    public ResponseEntity<?> getFilteredPosts(
            @QuerydslPredicate(root = TasksSquare.class) Predicate predicateSquare,
            @QuerydslPredicate(root = TasksSubstring.class) Predicate predicateSubstring) {
        return null;
    }
}
