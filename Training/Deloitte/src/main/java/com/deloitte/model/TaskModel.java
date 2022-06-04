package com.deloitte.model;

import com.deloitte.entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.parse;
import static java.util.stream.Collectors.toList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel {
    private Long id;
    private String description;
    private String modifytimestamp;
    private boolean completed;

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static TaskModel from(TaskEntity entity) {
        return new TaskModel(entity.getId(), entity.getDescription(), toStringDate(entity.getModifytimestamp()), entity.isCompleted());
    }

    public static List<TaskModel> from(Set<TaskEntity> entities) {
        return entities.stream()
                .map(e -> new TaskModel(e.getId(), e.getDescription(), toStringDate(e.getModifytimestamp()), e.isCompleted()))
                .collect(toList());
    }

    public static TaskEntity to(TaskModel taskModel) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setDescription(taskModel.getDescription());
        taskEntity.setCompleted(taskModel.isCompleted());
        String modifytimestamp = taskModel.getModifytimestamp();
        if (modifytimestamp != null) {
            taskEntity.setModifytimestamp(parse(modifytimestamp, formatter));
        } else {
            taskEntity.setModifytimestamp(now());
        }
        return taskEntity;
    }

    private static String toStringDate(LocalDateTime localDateTime) {
        return localDateTime.format(formatter);
    }
}