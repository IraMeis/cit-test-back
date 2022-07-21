package com.morena.citTestBack.service;

import com.morena.citTestBack.dto.DTasksSquare;
import com.morena.citTestBack.dto.DTasksSubstring;
import com.morena.citTestBack.util.JsonStringDTaskConvertor;
import com.morena.citTestBack.util.SolvingTasks;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@Service
public class FileService {

    /**
     * Checks and parse multipart file data into DTask, returns solved task
     * @param file multipart file wirh task
     * @return solved task
     */
    public ResponseEntity<?> solveFile (MultipartFile file){

        String json;
        var notParsedResponse = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

        if(file.isEmpty())
            return notParsedResponse;

        try {
            json = new String(file.getBytes(), StandardCharsets.UTF_8);
        }
        catch (Exception e) {
            return notParsedResponse;
        }

        var resSq = JsonStringDTaskConvertor.convertToDTasksSquare(json);
        var resStr = JsonStringDTaskConvertor.convertToDTasksSubstring(json);

        if((resSq.isPresent() && resStr.isPresent()) || resSq.isEmpty() && resStr.isEmpty())
            return notParsedResponse;
        else if (resSq.isPresent() && resSq.get().isCorrect()) {
            return ResponseEntity.ok(resSq.get());
        }
        else if (resStr.isPresent() && resStr.get().isCorrect()) {
            resStr.get().setArrayResult(
                    SolvingTasks.solveSubstring(resStr.get().getArray1(), resStr.get().getArray2()));
            return ResponseEntity.ok(resStr.get());
        }

        return notParsedResponse;
    }

    /**
     * @param dTasksSquare DTasksSquare
     * @return response with blob of dTasksSquare
     */
    public ResponseEntity<?> castSQtoFile (DTasksSquare dTasksSquare) {
        if(!dTasksSquare.isCorrectCodeCheck())
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        return objToFileResponse(dTasksSquare);
    }

    /**
     * @param dTasksSubstring DTasksSubstring
     * @return response with blob of dTasksSubstring
     */
    public ResponseEntity<?> castSTRtoFile (DTasksSubstring dTasksSubstring) {
        if(!dTasksSubstring.isCorrectCodeCheck())
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        return objToFileResponse(dTasksSubstring);
    }

    /**
     * Converts object into json String, returns ResponseEntity with String bytes
     */
    private<T> ResponseEntity<?> objToFileResponse(T obj) {

        var jsonStr = JsonStringDTaskConvertor.convertToJsonString(obj);

        if (jsonStr.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

        byte[] data = jsonStr.get().getBytes(StandardCharsets.UTF_8);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(data.length);
        headers.setContentDisposition(
                ContentDisposition
                        .builder("attachment")
                        .filename(jsonStr.hashCode() + ".json", StandardCharsets.UTF_8)
                        .build()
        );
        return ResponseEntity.ok().headers(headers).body(data);
    }
}
