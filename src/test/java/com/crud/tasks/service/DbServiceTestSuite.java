package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class DbServiceTestSuite {

    @Autowired
    DbService dbService;
    @MockBean
    TaskRepository taskRepository;

    @Test
    void getAllTasksTest() {
        //given
        Task task = new Task(1L, "test", "test");
        List<Task> taskList = List.of(task);
        when(taskRepository.findAll()).thenReturn(taskList);
        //When
        List<Task> allTasks = dbService.getAllTasks();
        //Then
        assertNotNull(allTasks);
        assertEquals(1, allTasks.size());
        assertEquals(task.getId(), allTasks.get(0).getId());
    }

    @Test
    void createTaskTest() throws TaskNotFoundException {
        //given
        Task task = new Task(1L, "task", "task");
        when(taskRepository.save(task)).thenReturn(task);
        //when
        Task savedTask =dbService.saveTask(task);
        //then
        assertNotNull(savedTask);
        assertEquals(1L,savedTask.getId());
}
    @Test
    void getTaskById() throws TaskNotFoundException {
        //Given
        Task task = new Task(2L, "test", "test");
        when(taskRepository.findById(2L)).thenReturn(Optional.of(task));
        //When
        Task taskById = dbService.getTask(2L);
        //Then
        assertNotNull(taskById);
        assertEquals(task.getId(), taskById.getId());
    }
}
