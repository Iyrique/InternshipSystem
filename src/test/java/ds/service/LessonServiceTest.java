package ds.service;

import ds.domain.Internship;
import ds.domain.Lesson;
import ds.repository.LessonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class LessonServiceTest {

    @Mock
    private LessonRepository lessonRepository;

    @InjectMocks
    private LessonServiceImpl lessonService;

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
    public void testSaveLesson() {
        when(lessonRepository.save(lesson)).thenReturn(lesson);
        Lesson result = lessonService.saveLesson(lesson);
        assertEquals(lesson, result);
    }

    @Test
    public void testUpdateLesson() {
        Lesson existingLesson = new Lesson();
        existingLesson.setId(1L);
        existingLesson.setName("Lesson 1");
        Lesson updatedLesson = new Lesson();
        updatedLesson.setId(1L);
        updatedLesson.setName("Updated Lesson 1");
        when(lessonRepository.getById(1L)).thenReturn(existingLesson);
        when(lessonRepository.save(existingLesson)).thenReturn(updatedLesson);
        Lesson result = lessonService.updateLesson(1L, updatedLesson);
        assertEquals(updatedLesson, result);
    }

    @Test
    public void testDeleteLesson() {
        lessonService.deleteLesson(2L);
        verify(lessonRepository).deleteById(2L);
    }

    @Test
    public void testGetAllLessons() {
        List<Lesson> lessons = Arrays.asList(lesson);
        when(lessonRepository.findAll()).thenReturn(lessons);
        List<Lesson> result = lessonService.getAllLessons();
        assertEquals(lessons, result);
    }

    @Test
    public void testGetLessonsByInternshipId() {
        List<Lesson> lessons = Arrays.asList(lesson);
        Long internshipId = lesson.getInternship().getId();
        when(lessonRepository.findAllByInternshipId(internshipId)).thenReturn(lessons);
        List<Lesson> result = lessonService.getLessonsByInternshipId(internshipId);
        assertEquals(lessons, result);
    }

    @Test
    public void testGetLessonsByDateRange() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 1, 1);

        List<Lesson> lessons = Arrays.asList(lesson);
        when(lessonRepository.findAllByDateTimeBetween(startDate, endDate)).thenReturn(lessons);
        List<Lesson> result = lessonService.getLessonsByDateRange(startDate, endDate);
        assertEquals(lessons, result);
    }
}
