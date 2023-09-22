package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    TaskMapper mapper;

    @Test
    void mapToTaskTest() {
        //given
        TaskDto taskDto = new TaskDto(1L, "Title", "content");
        //when
        Task task = mapper.mapToTask(taskDto);
        //then
        assertNotNull(task);
        assertEquals(1L, task.getId());
        assertEquals("Title", task.getTitle());
        assertEquals("content", task.getContent());
    }

    @Test
    void mapToTaskDtoTest() {
        //given
        Task task = new Task(1L, "Title", "content");
        //when
        TaskDto taskDto = mapper.mapToTaskDto(task);
        //then
        assertNotNull(taskDto);
        assertEquals(1L, taskDto.getId());
        assertEquals("Title", taskDto.getTitle());
        assertEquals("content", taskDto.getContent());

    }

    @Test
    void mapToTaskDtoList() {
        //given
        Task callJohn = new Task(1L, "call John", "call John at 7");
        Task meetJimmy = new Task(2L, "meet Jimmy", "meet Jimmy at 8");
        List<Task> taskList = new ArrayList<>();
        taskList.add(callJohn);
        taskList.add(meetJimmy);
        //when
        List<TaskDto> taskDtoList = mapper.mapToTaskDtoList(taskList);
        //then
        assertNotNull(taskDtoList);
        assertEquals(2,taskDtoList.size());
    }


}
