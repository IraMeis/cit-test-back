package com.morena.citTestBack.controller;

import com.morena.citTestBack.dto.DTasksSubstring;
import com.morena.citTestBack.service.TasksSubstringService;
import com.morena.citTestBack.util.SolvingTasks;
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
     * @param task DTasksSubstring, array1 and array2 required
     * @return 200 - ok, 406 - error related to data format
     */
    @PostMapping("/create")
    public ResponseEntity<?> createSubstring(@RequestBody DTasksSubstring task) {
        return tasksSubstringService.saveSubstringTask(task) ?
                ResponseEntity.ok().build() :
                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    /**
     * Solves given {@link DTasksSubstring}
     * /api/substring/solve POST
     * @param task DTasksSubstring, array1 and array2 required
     * @return DTasksSubstring with arrayResult, status 200 - ok, 406 - error related to data format
     */
    @PostMapping("/solve")
    public ResponseEntity<?> solve(@RequestBody DTasksSubstring task) {
        if(!task.isCorrect())
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

        task.setArrayResult(SolvingTasks.solveSubstring(task.getArray1(), task.getArray2()));
        return ResponseEntity.ok(task);
    }

}
