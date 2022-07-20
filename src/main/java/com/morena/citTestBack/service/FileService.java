package com.morena.citTestBack.service;

import com.morena.citTestBack.util.JsonStringToDTaskConvertor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@Service
public class FileService {

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

        var resSq = JsonStringToDTaskConvertor.convertToDTasksSquare(json);
        var resStr = JsonStringToDTaskConvertor.convertToDTasksSubstring(json);

        if((resSq.isPresent() && resStr.isPresent()) || resSq.isEmpty() && resStr.isEmpty())
            return notParsedResponse;
        else if (resSq.isPresent())
            return  ResponseEntity.ok(resSq.get());
        else return ResponseEntity.ok(resStr.get());

    }
}
