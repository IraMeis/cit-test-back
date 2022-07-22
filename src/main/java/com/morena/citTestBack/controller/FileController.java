package com.morena.citTestBack.controller;

import com.morena.citTestBack.dto.DTasksSquare;
import com.morena.citTestBack.dto.DTasksSubstring;
import com.morena.citTestBack.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    /**
     * Get solved task by data given in multipart file
     * /api/file/upload PATCH
     * @param file multipart file with DTask
     * @return solved DTask, 406 status if incorrect data
     */
    @PatchMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile file) {
        return fileService.solveFile(file);
    }

    /**
     * Download file with given DTasksSquare
     * /api/file/download/SQ PATCH
     * @param task RequestBody {@link DTasksSquare}
     * @return blob with json of task, 406 status if incorrect data
     */
    @PatchMapping("/download/SQ")
    public ResponseEntity<?> download(@RequestBody DTasksSquare task) {
        return fileService.castSQtoFile(task);
    }

    /**
     * Download file with given DTasksSubstring
     * /api/file/download/SUB PATCH
     * @param task RequestBody {@link DTasksSubstring}
     * @return blob with json of task, 406 status if incorrect data
     */
    @PatchMapping("/download/SUB")
    public ResponseEntity<?> download(@RequestBody DTasksSubstring task) {
        return fileService.castSTRtoFile(task);
    }
}
