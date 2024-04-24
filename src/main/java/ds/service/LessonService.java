package ds.service;

import ds.domain.Lesson;

import java.time.LocalDate;
import java.util.List;

public interface LessonService {

    Lesson saveLesson(Lesson lesson);

    Lesson updateLesson(Long id, Lesson lesson);

    void deleteLesson(Long id);

    List<Lesson> getAllLessons();

    List<Lesson> getLessonsByInternshipId(Long internshipId);

    List<Lesson> getLessonsByDateRange(LocalDate startDate, LocalDate endDate);
}
