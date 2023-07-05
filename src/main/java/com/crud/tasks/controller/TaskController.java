package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import com.crud.tasks.mapper.TaskMapper;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
public class TaskController {


    private final DbService service;
    private final TaskMapper taskMapper;



    @GetMapping
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @GetMapping(value="{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) {
      Task task =service.getTask(taskId);

        return new TaskDto(task.getId(), task.getTitle(), task.getContent());
    }

    @DeleteMapping
    public void deleteTask(Long taskId) {
    }

    @PutMapping
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "upgraded test title", "edited test_content");
    }

    @PostMapping
    public void createTask(TaskDto taskDto) {
    }
}
