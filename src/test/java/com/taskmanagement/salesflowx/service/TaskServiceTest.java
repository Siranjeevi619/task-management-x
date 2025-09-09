package com.taskmanagement.salesflowx.service;

import com.taskmanagement.salesflowx.dto.TaskDTO;
import com.taskmanagement.salesflowx.entity.Task;
import com.taskmanagement.salesflowx.exception.TaskNotFoundException;
import com.taskmanagement.salesflowx.repository.TaskRepository;
import com.taskmanagement.salesflowx.utils.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        task = new Task();
        task.setId("1");
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setStatus(Status.PENDING);
        task.setCreatedAt(new Date());
    }

    @Test
    void testCreateTask() {
        TaskDTO dto = new TaskDTO();
        dto.setTitle("New Task");
        dto.setDescription("New Description");
        dto.setStatus(Status.PENDING);

        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task savedTask = taskService.createTask(dto);

        assertNotNull(savedTask);
        assertEquals("New Task", savedTask.getTitle());
        assertEquals(Status.PENDING, savedTask.getStatus());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testGetAllTasks() {
        Task t1 = new Task("1", "Task 1", "Desc1", Status.PENDING, new Date(), null);
        Task t2 = new Task("2", "Task 2", "Desc2", Status.IN_PROGRESS, new Date(), null);

        when(taskRepository.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(2, tasks.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void testGetTaskById_Found() {
        when(taskRepository.findById("1")).thenReturn(java.util.Optional.of(task));

        Task result = taskService.getTaskById("1");

        assertNotNull(result);
        assertEquals("Test Task", result.getTitle());
        verify(taskRepository, times(1)).findById("1");
    }

    @Test
    void testGetTaskById_NotFound() {
        when(taskRepository.findById("99")).thenReturn(java.util.Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById("99"));
        verify(taskRepository, times(1)).findById("99");
    }

    @Test
    void testUpdateTaskById_Found() {
        TaskDTO dto = new TaskDTO("Updated Task", "Updated Desc", Status.IN_PROGRESS);
        when(taskRepository.findById("1")).thenReturn(java.util.Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task updatedTask = taskService.updateTaskById("1", dto);

        assertEquals("Updated Task", updatedTask.getTitle());
        assertEquals(Status.IN_PROGRESS, updatedTask.getStatus());
        verify(taskRepository, times(1)).findById("1");
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testUpdateTaskById_NotFound() {
        TaskDTO dto = new TaskDTO("Updated Task", "Updated Desc", Status.IN_PROGRESS);
        when(taskRepository.findById("99")).thenReturn(java.util.Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.updateTaskById("99", dto));
        verify(taskRepository, times(1)).findById("99");
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void testDeleteTaskById_Found() {
        when(taskRepository.existsById("1")).thenReturn(true);
        doNothing().when(taskRepository).deleteById("1");

        boolean deleted = taskService.deleteTaskById("1");

        assertTrue(deleted);
        verify(taskRepository, times(1)).deleteById("1");
    }

    @Test
    void testDeleteTaskById_NotFound() {
        when(taskRepository.existsById("99")).thenReturn(false);

        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTaskById("99"));
        verify(taskRepository, never()).deleteById("99");
    }
}
