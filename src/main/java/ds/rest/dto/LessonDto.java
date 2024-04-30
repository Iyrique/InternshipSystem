package ds.rest.dto;

import ds.domain.Internship;
import ds.domain.Lesson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto {

    private Long id;

    private String name;
    private String description;
    private LocalDate dateTime;
    private String status;
    private Internship internship;

    public static Lesson toDomainObject(LessonDto lessonDto) {
        return new Lesson(lessonDto.getId(), lessonDto.getName(), lessonDto.getDescription(), lessonDto.getDateTime(),
                lessonDto.getStatus(), lessonDto.getInternship());
    }

    public static LessonDto toDto(Lesson lesson) {
        return new LessonDto(lesson.getId(), lesson.getName(), lesson.getDescription(), lesson.getDateTime(),
                lesson.getStatus(), lesson.getInternship());
    }
}
