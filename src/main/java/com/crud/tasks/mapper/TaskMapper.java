package com.crud.tasks.mapper;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {
    public TaskDto mapToTaskDto(final Task task){
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getContent());
    }

    public Task mapToTask(final TaskDto taskDto){
        return new Task(
                taskDto.getId(),
                taskDto.getTitle(),
                taskDto.getContent());
    }

    public List<Task> mapToTaskList(final List<TaskDto> taskDtoList){
        return taskDtoList.stream()
                .map(t -> new Task(t.getId(), t.getTitle(), t.getContent()))
                .collect(Collectors.toList());
    }
}
