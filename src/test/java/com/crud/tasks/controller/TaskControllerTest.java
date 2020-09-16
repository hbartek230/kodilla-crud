package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;

    @Test
    public void shouldReturnEmptyTaskList() throws Exception {
        // given
        List<Task> taskList = new ArrayList<>();
        when(taskMapper.mapToTaskList(new ArrayList<>())).thenReturn(taskList);

        // when & then
        mockMvc.perform(get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldReturnTasksList() throws Exception {
        List<Task> tasksList = Collections.singletonList(new Task(1L, "Test title", "Test content"));
        when(taskMapper.mapToTaskList(anyList())).thenReturn(tasksList);

        // when & then
        mockMvc.perform(get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1L)))
                .andExpect(jsonPath("$[0].title", is("Test title")))
                .andExpect(jsonPath("$[0].content", is("Test content")));
    }

    @Test
    public void shouldReturnSingleTask() throws Exception {
        Task testedTask = new Task(1L, "Test title", "Test content");
        TaskDto testedTaskDto = new TaskDto(1L, "Test title", "Test content");
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(testedTask);
        when(dbService.getTaskById(1L)).thenReturn(Optional.of(testedTaskDto));

        // when & then
        mockMvc.perform(get("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("taskId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test title")))
                .andExpect(jsonPath("$.content", is("Test content")));
    }

    @Test
    public void shouldNotReturnAnyTask() throws Exception {
        // given
        when(dbService.getTaskById(1L)).thenReturn(Optional.empty());

        // when & then
        mockMvc.perform(get("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("taskId", "1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        // given
        TaskDto testedTaskDto = new TaskDto(1L, "Test title", "Test content");
        when(dbService.getTaskById(1L)).thenReturn(Optional.of(testedTaskDto));

        // when & then
        mockMvc.perform(delete("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("taskId", "1"))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldNotDeleteTaskNotExist() throws Exception {
        //given
        when(dbService.getTaskById(1L)).thenReturn(Optional.empty());

        // when & then
        mockMvc.perform(delete("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("taskId", "1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        // given
        Task testedTask = new Task(1L, "Test title", "Test content");
        TaskDto testedTaskDto = new TaskDto(1L, "Test title", "Test content");
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(testedTaskDto);
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(testedTask);
        when(dbService.saveTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(testedTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(testedTask);

        // when & then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test title")))
                .andExpect(jsonPath("$.content", is("Test content")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        // given
        Task testedTask = new Task(1L, "Test title", "Test content");
        TaskDto testedTaskDto = new TaskDto(1L, "Test title", "Test content");
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(testedTaskDto);
        when(dbService.saveTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(testedTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(testedTask);

        // when & then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .contentType(jsonContent))
                .andExpect(status().is(200));
    }

}