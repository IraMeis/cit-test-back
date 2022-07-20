package com.morena.citTestBack.controller;

import com.morena.citTestBack.dto.DTasksSubstring;
import com.morena.citTestBack.service.TasksSubstringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/substring")
@RequiredArgsConstructor
public class TasksSubstringController {

    private final TasksSubstringService tasksSubstringService;

    /**
     * Creates substring task
     * /api/substring/create POST
     * @param task json with 2 lists of strings - array1 and array2
     */
    @PostMapping("/create")
    public ResponseEntity<?> createSubstring(@RequestBody DTasksSubstring task) {
        return tasksSubstringService.saveSubstringTask(task) ?
                ResponseEntity.ok().build() :
                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    /**
     * /api/substring/getTask/{id}
     * @param id substring task unique id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTask(@PathVariable Long id) {
        return null;
    }

    /**
     * /api/substring/solve
     * @param task json with 2 lists of strings - array1 and array2
     * @return
     */
    @PostMapping("/solve")
    public ResponseEntity<?> solve(@RequestBody DTasksSubstring task) {
        return null;
    }

}
