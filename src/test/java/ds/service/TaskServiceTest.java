package ds.service;

import ds.domain.Internship;
import ds.domain.Task;
import ds.repository.TaskRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;

    @BeforeEach
    public void prepareTestData() {
        task = Task.builder()
                .id(1L)
                .gitlabRepository("test")
                .name("test")
                .internship(Internship.builder()
                        .id(1L)
                        .description("Test")
                        .endDate(LocalDate.of(2024, 5, 10))
                        .endDateRecording(LocalDate.of(2024, 4, 26))
                        .name("IntTest")
                        .startDate(LocalDate.of(2024, 4, 27))
                        .status("ACTIVE")
                        .build())
                .build();
    }

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = Arrays.asList(task);
        when(taskRepository.findAll()).thenReturn(tasks);
        List<Task> result = taskService.getAllTasks();
        assertEquals(tasks, result);
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void testGetTaskById() {
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));
        Task result = taskService.getTaskById(task.getId());
        assertEquals(task, result);
    }

    @Test
    public void testCreateTask() {
        when(taskRepository.save(task)).thenReturn(task);
        Task result = taskService.createTask(task);
        assertNotNull(result);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testUpdateTask() {
        Long id = 1L;
        Task task = new Task();
        task.setId(id);

        Task existingTask = new Task();
        existingTask.setId(id);

        when(taskRepository.findById(id)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(existingTask)).thenReturn(existingTask);

        Task updatedTask = taskService.updateTask(id, task);
        assertNotNull(updatedTask);
        assertEquals(task.getId(), updatedTask.getId());
        verify(taskRepository, times(1)).findById(id);
        verify(taskRepository, times(1)).save(existingTask);
    }

    @Test
    public void testDeleteTask() {
        Long id = 1L;
        taskService.deleteTask(id);
        verify(taskRepository, times(1)).deleteById(id);
    }

}
