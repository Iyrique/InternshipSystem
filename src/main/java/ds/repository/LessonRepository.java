package ds.repository;

import ds.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Lesson getById(Long id);

    List<Lesson> findAllByInternshipId(Long id);

    List<Lesson> findAllByDateTimeBetween(LocalDate startDate, LocalDate endDate);
}
