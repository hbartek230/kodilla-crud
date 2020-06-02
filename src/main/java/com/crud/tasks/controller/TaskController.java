package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    public List<TaskDto> getTasks(){
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public TaskDto getTask(Long taskId){
        return new TaskDto(1L, "test title", "test_content");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteTask(@PathVariable("id") Long taskId){

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/")
    public TaskDto updateTask(TaskDto taskDto){
        return new TaskDto(1L, "edited test title", "test content");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public void createTask(TaskDto taskDto){

    }
}
