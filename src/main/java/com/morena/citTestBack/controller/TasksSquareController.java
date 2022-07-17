package com.morena.citTestBack.controller;

import com.morena.citTestBack.dto.DTasksSquare;
import com.morena.citTestBack.service.TasksSquareService;
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
     * @param task json with list of long - inputMatrix
     */
    @PostMapping("/create")
    public ResponseEntity<?> createSquare(@RequestBody DTasksSquare task) {
        return tasksSquareService.saveSquareTask(task) ?
                ResponseEntity.ok().build() :
                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    /**
     * /api/square/getTask/{id}
     * @param id square task unique id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTask(@PathVariable Long id) {
        return null;
    }

    /**
     * /api/square/solve
     * @param task json with list of long - inputMatrix

     */
    @PostMapping("/solve")
    public ResponseEntity<?> solve(@RequestBody DTasksSquare task) {
        return null;
    }

    @PatchMapping("/upload")
    public ResponseEntity<?> upload() {
        return null;
    }

    @PatchMapping("/download")
    public ResponseEntity<?> download() {
        return null;
    }
}
