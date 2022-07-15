package com.morena.citTestBack.controller;

import com.morena.citTestBack.dto.DTasksSquare;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/square")
@RequiredArgsConstructor
public class TasksSquareController {

    /**
     * /api/square/create
     * @param task json with list of long - inputMatrix
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> createSquare(@RequestBody DTasksSquare task) {
        return null;
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
     * @return
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
