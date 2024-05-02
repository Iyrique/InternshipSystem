package ds.rest.dto;

import ds.domain.Internship;
import ds.domain.Lesson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность занятия")
public class LessonDto {

    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    @Schema(description = "Тема занятия", example = "История Java, переменные, типы данных, операторы")
    private String name;

    @Schema(description = "Описание занятия", example = "История и развитие языка Java")
    private String description;

    @Schema(description = "Дата занятия", example = "02.05.2024")
    private LocalDate dateTime;

    @Schema(description = "Статус", example = "ACTUAL")
    private String status;

    @Schema(description = "Стажировка", example = "internship:{...}")
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
