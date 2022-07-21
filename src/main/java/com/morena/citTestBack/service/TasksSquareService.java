package com.morena.citTestBack.service;

import com.morena.citTestBack.dto.DTask;
import com.morena.citTestBack.dto.DTasksSquare;
import com.morena.citTestBack.entity.TaskSquareElements;
import com.morena.citTestBack.entity.TasksSquare;
import com.morena.citTestBack.repository.TaskSquareElementsRepository;
import com.morena.citTestBack.repository.TasksSquareRepository;
import com.morena.citTestBack.util.ConstantUtil;
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
public class TasksSquareService {

    private final TasksSquareRepository tasksSquareRepository;
    private final TaskSquareElementsRepository taskSquareElementsRepository;

    private static final LocalDateTimeConvertor convertor = new LocalDateTimeConvertor();

    /**
     * Saves task and its elements
     * @return boolean isSaved
     */
    public boolean saveSquareTask(DTasksSquare dTasksSquare){
        if(!dTasksSquare.isCorrect())
            return false;

        TasksSquare taskEntity = new TasksSquare();
        tasksSquareRepository.save(taskEntity);

        Set<TaskSquareElements> elemsSet = new HashSet<>();

        for (int i = 0; i < dTasksSquare.getInputMatrix().size(); ++i) {
            TaskSquareElements taskElem = new TaskSquareElements();
            taskElem.setTask(taskEntity);
            taskElem.setMatrixEntry(dTasksSquare.getInputMatrix().get(i));
            taskElem.setOrder((long) i);
            elemsSet.add(taskElem);
        }
        taskSquareElementsRepository.saveAll(elemsSet);
        return true;
    }

    public List<DTask> findByPredicate (Predicate predicate){
        List<TasksSquare> tasksSquares = new ArrayList<>();
        tasksSquareRepository.findAll(predicate).forEach(tasksSquares::add);

        List<DTask> dTasksSquares = new ArrayList<>();

        tasksSquares.forEach(task -> {

            List<Long> matr = new ArrayList<>();

            if(task.getElements() != null &&  task.getElements().size() == ConstantUtil.getLineMatrixSize()) {

                for (int i = 0; i < task.getElements().size(); i++)
                    matr.add(0L);

                task.getElements().forEach(elem ->
                        matr.set(elem.getOrder().intValue(), elem.getMatrixEntry()));

                dTasksSquares.add(DTasksSquare.builder()
                        .id(task.getUniqueId())
                        .uuid(task.getUuid())
                        .createdTimestamp(convertor.convert(task.getCreatedTimestamp()))
                        .modifiedTimestamp(convertor.convert(task.getModifiedTimestamp()))
                        .inputMatrix(matr)
                        .build());
            }
        });

        return dTasksSquares;
    }
}
