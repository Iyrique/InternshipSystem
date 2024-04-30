package ds.repository;

import ds.domain.Internship;
import ds.domain.Lesson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LessonRepositoryTest {

    @Mock
    private LessonRepository lessonRepository;

    private static Lesson lesson;

    @BeforeAll
    public static void prepareTestData() {
        lesson = Lesson.builder()
                .id(2L)
                .dateTime(LocalDate.of(2024, 4, 27))
                .description("Test")
                .name("Test")
                .status("Test")
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
        when(lessonRepository.getById(2L)).thenReturn(lesson);
        Lesson result = lessonRepository.getById(2L);
        assertEquals(lesson, result);
    }

    @Test
    public void testFindALlByInternshipId() {
        List<Lesson> lessons = Arrays.asList(lesson);
        when(lessonRepository.findAllByInternshipId(1L)).thenReturn(lessons);
        List<Lesson> result = lessonRepository.findAllByInternshipId(1L);
        assertEquals(lessons, result);
    }

    @Test
    public void testFindAllByDateTimeBetween() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 1, 1);

        List<Lesson> lessons = Arrays.asList(lesson);

        when(lessonRepository.findAllByDateTimeBetween(startDate, endDate)).thenReturn(lessons);
        List<Lesson> result = lessonRepository.findAllByDateTimeBetween(startDate, endDate);
        assertEquals(lessons, result);
    }
}
