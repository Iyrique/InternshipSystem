package ds.rest.dto;

import ds.domain.Internship;
import ds.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;
    private String name;
    private String gitlabRepository;
    private Internship internship;

    public static Task toDomainObject(TaskDto taskDto) {
        return new Task(taskDto.getId(), taskDto.getName(), taskDto.getGitlabRepository(), taskDto.getInternship());
    }

    public static TaskDto toDto(Task task) {
        return new TaskDto(task.getId(), task.getName(), task.getGitlabRepository(), task.getInternship());
    }
}
