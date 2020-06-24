package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/task")
@CrossOrigin(origins = "*")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<Task> getTasks() {
        return taskMapper.mapToTaskList(service.getAllTask());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public Task getTask(@RequestParam Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTask(service.getTaskById(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(@RequestParam Long taskId) {
        service.deleteTask(taskId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public Task updateTask(@RequestBody Task task) {
        return taskMapper.mapToTask(service.saveTask(taskMapper.mapToTaskDto(task)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody Task task) {
        service.saveTask(taskMapper.mapToTaskDto(task));
    }
}
