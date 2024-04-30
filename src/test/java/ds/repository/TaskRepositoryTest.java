package ds.repository;

import ds.domain.Internship;
import ds.domain.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskRepositoryTest {
    @Mock
    private TaskRepository taskRepository;

    private static Task task;

    @BeforeAll
    public static void prepareTestData() {
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
    public void testGetById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Optional<Task> result = taskRepository.findById(1L);
        assertEquals(task, result.orElse(null));
    }
}
