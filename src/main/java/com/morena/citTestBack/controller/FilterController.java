package com.morena.citTestBack.controller;

import com.morena.citTestBack.entity.TasksSquare;
import com.morena.citTestBack.entity.TasksSubstring;
import com.morena.citTestBack.service.TasksSquareService;
import com.morena.citTestBack.service.TasksSubstringService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/filter")
@RequiredArgsConstructor
public class FilterController {

    private final TasksSquareService tasksSquareService;
    private final TasksSubstringService tasksSubstringService;

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
            @QuerydslPredicate(root = TasksSquare.class) Predicate predicateSquare,
            @QuerydslPredicate(root = TasksSubstring.class) Predicate predicateSubstring) {

        if((inSquare && inSubstring) || (!inSquare && !inSubstring)) {
           return ResponseEntity.ok(tasksSquareService.findByPredicate(predicateSquare)
                    .addAll(tasksSubstringService.findByPredicate(predicateSubstring)));
        }
        else if (inSquare)
            return ResponseEntity.ok(tasksSquareService.findByPredicate(predicateSquare));
        else
            return ResponseEntity.ok(tasksSubstringService.findByPredicate(predicateSubstring));
    }
}
