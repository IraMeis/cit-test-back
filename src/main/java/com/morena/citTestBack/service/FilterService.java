package com.morena.citTestBack.service;

import com.morena.citTestBack.dto.DTask;
import com.morena.citTestBack.entity.QTasksSquare;
import com.morena.citTestBack.entity.QTasksSubstring;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterService {
    private final TasksSquareService tasksSquareService;
    private final TasksSubstringService tasksSubstringService;

    public ResponseEntity<?> getFilteredTasks(Boolean inSquare,
                                              Boolean inSubstring,
                                              Predicate predicateSquare,
                                              Predicate predicateSubstring,
                                              LocalDate from,
                                              LocalDate to) {

        //add is Deleted check
        predicateSquare =  ExpressionUtils.allOf(predicateSquare,
                QTasksSquare.tasksSquare.isDeleted.isFalse());
        predicateSubstring =  ExpressionUtils.allOf(predicateSubstring,
                QTasksSubstring.tasksSubstring.isDeleted.isFalse());

        //created after
        if(from != null) {
            predicateSquare = ExpressionUtils.allOf(predicateSquare,
                    QTasksSquare.tasksSquare.createdTimestamp.after(from.atStartOfDay()));
            predicateSubstring =  ExpressionUtils.allOf(predicateSubstring,
                    QTasksSubstring.tasksSubstring.createdTimestamp.after(from.atStartOfDay()));
        }

        //created before
        if(to != null) {
            predicateSquare = ExpressionUtils.allOf(predicateSquare,
                    QTasksSquare.tasksSquare.createdTimestamp.before(to.atTime(23,59,59)));
            predicateSubstring =  ExpressionUtils.allOf(predicateSubstring,
                    QTasksSubstring.tasksSubstring.createdTimestamp.before(to.atTime(23,59,59)));
        }

        //type - substring & square
        if((inSquare && inSubstring) || (!inSquare && !inSubstring)) {
            List<DTask> result = tasksSquareService.findByPredicate(predicateSquare);
            result.addAll(tasksSubstringService.findByPredicate(predicateSubstring));
            return ResponseEntity.ok(result);
        }
        //type - square
        else if (inSquare)
            return ResponseEntity.ok(tasksSquareService.findByPredicate(predicateSquare));
        //type - substring
        else
            return ResponseEntity.ok(tasksSubstringService.findByPredicate(predicateSubstring));
    }
}
