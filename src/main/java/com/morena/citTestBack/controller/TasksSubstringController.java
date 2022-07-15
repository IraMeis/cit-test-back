package com.morena.citTestBack.controller;

import com.morena.citTestBack.dto.DTasksSubstring;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/substring")
@RequiredArgsConstructor
public class TasksSubstringController {

    /**
     * /api/substring/create
     * @param task json with 2 lists of strings - array1 and array2
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> createSubstring(@RequestBody DTasksSubstring task) {
        return null;
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

    @PatchMapping("/upload")
    public ResponseEntity<?> upload() {
        return null;
    }

    @PatchMapping("/download")
    public ResponseEntity<?> download() {
        return null;
    }
}
