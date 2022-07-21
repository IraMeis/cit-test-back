package com.morena.citTestBack.service;

import com.morena.citTestBack.dto.DTasksSquare;
import com.morena.citTestBack.dto.DTasksSubstring;
import com.morena.citTestBack.enums.TaskTypeEnum;
import com.morena.citTestBack.util.ConstantUtil;
import com.morena.citTestBack.util.JsonStringDTaskConvertor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

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

        var resSq = JsonStringDTaskConvertor.convertToDTasksSquare(json);
        var resStr = JsonStringDTaskConvertor.convertToDTasksSubstring(json);

        if((resSq.isPresent() && resStr.isPresent()) || resSq.isEmpty() && resStr.isEmpty())
            return notParsedResponse;
        else if (resSq.isPresent())
            return  ResponseEntity.ok(resSq.get());
        else return ResponseEntity.ok(resStr.get());

    }

    public ResponseEntity<?> castSQtoFile (DTasksSquare dTasksSquare) {

        if(dTasksSquare.getTypeCode() == null ||
                        dTasksSquare.getInputMatrix().isEmpty() ||
                        dTasksSquare.getInputMatrix().size() != ConstantUtil.getLineMatrixSize() ||
                        !Objects.equals(dTasksSquare.getTypeCode(), TaskTypeEnum.taskSquare.getCode()))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

        return objToFileResponse(dTasksSquare);
    }


    public ResponseEntity<?> castSTRtoFile (DTasksSubstring dTasksSubstring) {

        if(dTasksSubstring.getTypeCode() == null ||
                dTasksSubstring.getArray2().isEmpty() || dTasksSubstring.getArray1().isEmpty() ||
                !Objects.equals(dTasksSubstring.getTypeCode(), TaskTypeEnum.taskSubstring.getCode()))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

        return objToFileResponse(dTasksSubstring);
    }

    private<T> ResponseEntity<?> objToFileResponse(T task) {

        var jsonStr = JsonStringDTaskConvertor.convertToJsonString(task);

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
