package com.morena.citTestBack.controller;

import com.morena.citTestBack.dto.DTasksSquare;
import com.morena.citTestBack.service.TasksSquareService;
import com.morena.citTestBack.util.SolvingTasks;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/square")
@RequiredArgsConstructor
public class TasksSquareController {

    private final TasksSquareService tasksSquareService;

    /**
     * Creates square task
     * /api/square/create POST
     * @param task DTasksSquare, inputMatrix required
     * @return 200 - ok, 406 - error related to data format
     */
    @PostMapping("/create")
    public ResponseEntity<?> createSquare(@RequestBody DTasksSquare task) {
        return tasksSquareService.saveSquareTask(task) ?
                ResponseEntity.ok().build() :
                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    /**
     * Solves given {@link DTasksSquare}
     * /api/square/solve POST
     * @param task DTasksSquare, inputMatrix required
     * @return DTasksSquare with outputMatrix, status 200 - ok, 406 - error related to data format
     */
    @PostMapping("/solve")
    public ResponseEntity<?> solve(@RequestBody DTasksSquare task) {
        if(!task.isCorrect())
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

        var solved = SolvingTasks.solveSquare3X3(task.getInputMatrix());
        if(solved.isEmpty())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        task.setOutputMatrix(solved.get().subList(1, solved.get().size()));
        task.setCost(solved.get().get(0));
        return ResponseEntity.ok(task);
    }

}
