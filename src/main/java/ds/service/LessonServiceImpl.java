package ds.service;

import ds.domain.Lesson;
import ds.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService{

    private final LessonRepository lessonRepository;

    @Override
    @Transactional
    public Lesson saveLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    @Transactional
    public Lesson updateLesson(Long id, Lesson lesson) {
        Lesson existingLesson = lessonRepository.getById(id);
        if (existingLesson != null) {
            existingLesson.setName(lesson.getName());
            existingLesson.setDescription(lesson.getDescription());
            existingLesson.setDateTime(lesson.getDateTime());
            return lessonRepository.save(existingLesson);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public List<Lesson> getLessonsByInternshipId(Long internshipId) {
        return lessonRepository.findAllByInternshipId(internshipId);
    }

    @Override
    public List<Lesson> getLessonsByDateRange(LocalDate startDate, LocalDate endDate) {
        return lessonRepository.findAllByDateTimeBetween(startDate, endDate);
    }
}
