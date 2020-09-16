package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    public List<Task> getTasks() {
        return taskMapper.mapToTaskList(service.getAllTask());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{taskId}")
    public Task getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTask(service.getTaskById(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        service.deleteTask(taskId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks")
    public Task updateTask(@RequestBody Task task) {
        return taskMapper.mapToTask(service.saveTask(taskMapper.mapToTaskDto(task)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tasks", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody Task task) {
        service.saveTask(taskMapper.mapToTaskDto(task));
    }
}
