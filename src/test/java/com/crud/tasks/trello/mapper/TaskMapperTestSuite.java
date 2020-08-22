package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testTaskMapper() {
        // given
        Task task1 = new Task(1L, "test_title", "test_content");
        TaskDto taskDto1 = new TaskDto(1L, "testing_title", "testing_content");
        List<TaskDto> taskList1 = Collections.singletonList(taskDto1);

        // when
        Task task2 = taskMapper.mapToTask(taskDto1);
        TaskDto taskDto2 = taskMapper.mapToTaskDto(task1);
        List<Task> taskList2 = taskMapper.mapToTaskList(taskList1);

        // then
        assertEquals(task1.getId(), taskDto2.getId());
        assertEquals(task1.getTitle(), taskDto2.getTitle());
        assertEquals(task1.getContent(), taskDto2.getContent());

        assertEquals(task2.getId(), taskDto1.getId());
        assertEquals(task2.getTitle(), taskDto1.getTitle());
        assertEquals(task2.getContent(), taskDto1.getContent());

        taskList2.forEach(task -> {
            assertEquals(task.getId(), taskList1.get(0).getId());
            assertEquals(task.getTitle(), taskList1.get(0).getTitle());
            assertEquals(task.getContent(), taskList1.get(0).getContent());
        });
    }
}
