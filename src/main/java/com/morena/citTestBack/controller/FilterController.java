package com.morena.citTestBack.controller;

import com.morena.citTestBack.entity.TasksSquare;
import com.morena.citTestBack.entity.TasksSubstring;
import com.morena.citTestBack.service.FilterService;
import com.morena.citTestBack.util.LocalDateConvertor;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/filter")
@RequiredArgsConstructor
public class FilterController {

    private final FilterService filterService;

    /**
     * Filter tasks by given qdsl predicate (using BaseModelEntity fields) and task including params
     * /api/filter GET
     * @param inSquare param of including square tasks
     * @param inSubstring param of including substring tasks
     * @param predicateSquare qdsl predicate
     * @param predicateSubstring qdsl predicate
     * @return List of DTask
     */
    @GetMapping()
    public ResponseEntity<?> getFilteredTasks(
            @RequestParam(defaultValue = "false") Boolean inSquare,
            @RequestParam(defaultValue = "false") Boolean inSubstring,
            @RequestParam(required = false) @DateTimeFormat(pattern = LocalDateConvertor.dateFormat) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(pattern = LocalDateConvertor.dateFormat) LocalDate to,
            @QuerydslPredicate(root = TasksSquare.class) Predicate predicateSquare,
            @QuerydslPredicate(root = TasksSubstring.class) Predicate predicateSubstring) {

        return filterService.getFilteredTasks(inSquare, inSubstring, predicateSquare, predicateSubstring, from, to);
    }
}
