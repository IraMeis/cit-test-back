package com.morena.citTestBack.service;

import com.morena.citTestBack.dto.DTask;
import com.morena.citTestBack.dto.DTasksSubstring;
import com.morena.citTestBack.entity.TaskSubstringElements;
import com.morena.citTestBack.entity.TasksSubstring;
import com.morena.citTestBack.enums.DictTaskSubstringElementTypesEnum;
import com.morena.citTestBack.repository.DictTaskSubstringElementTypesRepository;
import com.morena.citTestBack.repository.TaskSubstringElementsRepository;
import com.morena.citTestBack.repository.TasksSubstringRepository;
import com.morena.citTestBack.util.LocalDateTimeConvertor;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TasksSubstringService {

    private final TasksSubstringRepository tasksSubstringRepository;
    private final TaskSubstringElementsRepository taskSubstringElementsRepository;
    private final DictTaskSubstringElementTypesRepository dictTaskSubstringElementTypesRepository;

    private static final LocalDateTimeConvertor convertor = new LocalDateTimeConvertor();

    /**
     * Saves task and its elements
     * @return boolean isSaved
     */
    public boolean saveSubstringTask(DTasksSubstring dTasksSubstring){
        if(dTasksSubstring.getArray1() == null ||dTasksSubstring.getArray2() == null
                || dTasksSubstring.getArray2().isEmpty() || dTasksSubstring.getArray1().isEmpty())
            return false;

        var typeA1 =
                dictTaskSubstringElementTypesRepository.findByCodeAndIsDeletedIsFalse(
                        DictTaskSubstringElementTypesEnum.firstArrElem.getCode());
        var typeA2 =
                dictTaskSubstringElementTypesRepository.findByCodeAndIsDeletedIsFalse(
                DictTaskSubstringElementTypesEnum.secondArrElem.getCode());

        if(typeA1.isEmpty() || typeA2.isEmpty())
            return false;

        TasksSubstring taskEntity = new TasksSubstring();
        tasksSubstringRepository.save(taskEntity);

        Set<TaskSubstringElements> elemsSet = new HashSet<>();

        dTasksSubstring.getArray1().forEach(str -> {
            TaskSubstringElements taskElem = new TaskSubstringElements();
            taskElem.setTask(taskEntity);
            taskElem.setElementType(typeA1.get());
            taskElem.setStringArrayEntry(str);
            elemsSet.add(taskElem);
        });

        dTasksSubstring.getArray2().forEach(str -> {
            TaskSubstringElements taskElem = new TaskSubstringElements();
            taskElem.setTask(taskEntity);
            taskElem.setElementType(typeA2.get());
            taskElem.setStringArrayEntry(str);
            elemsSet.add(taskElem);
        });
        taskSubstringElementsRepository.saveAll(elemsSet);
        return true;
    }

    public List<DTask> findByPredicate (Predicate predicate){
        List<TasksSubstring> tasksSubstrings = new ArrayList<>();
        tasksSubstringRepository.findAll(predicate).forEach(tasksSubstrings::add);

        List<DTask> dTasksSubstrings = new ArrayList<>();

        tasksSubstrings.forEach(task -> {
            List<String> ar1 = new ArrayList<>();
            List<String> ar2 = new ArrayList<>();

            if(task.getElements() != null && !task.getElements().isEmpty()) {
                task.getElements().forEach(elem -> {
                    if (elem.getElementType().getCode().equals(DictTaskSubstringElementTypesEnum.secondArrElem.getCode()))
                        ar2.add(elem.getStringArrayEntry());
                    else if (elem.getElementType().getCode().equals(DictTaskSubstringElementTypesEnum.firstArrElem.getCode()))
                        ar1.add(elem.getStringArrayEntry());
                });

                dTasksSubstrings.add(DTasksSubstring.builder()
                        .id(task.getUniqueId())
                        .uuid(task.getUuid())
                        .createdTimestamp(convertor.convert(task.getCreatedTimestamp()))
                        .modifiedTimestamp(convertor.convert(task.getModifiedTimestamp()))
                        .array1(ar1)
                        .array2(ar2)
                        .build());
            }
        });

        return dTasksSubstrings;
    }

}
