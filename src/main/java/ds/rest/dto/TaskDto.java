package ds.rest.dto;

import ds.domain.Internship;
import ds.domain.Task;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность задачи")
public class TaskDto {

    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    @Schema(description = "Название задачи", example = "Система управления стажировками")
    private String name;

    @Schema(description = "Репозиторий задачи", example = "project-2024")
    private String gitlabRepository;

    @Schema(description = "Стажировка", example = "internship:{...}")
    private Internship internship;

    public static Task toDomainObject(TaskDto taskDto) {
        return new Task(taskDto.getId(), taskDto.getName(), taskDto.getGitlabRepository(), taskDto.getInternship());
    }

    public static TaskDto toDto(Task task) {
        return new TaskDto(task.getId(), task.getName(), task.getGitlabRepository(), task.getInternship());
    }
}
