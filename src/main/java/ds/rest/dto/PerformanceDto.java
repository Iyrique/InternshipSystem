package ds.rest.dto;

import ds.domain.Participant;
import ds.domain.Performance;
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
@Schema(description = "Сущность успеваемости")
public class PerformanceDto {

    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    @Schema(description = "Сущность ученика", example = "{'id':'1',..., 'course':'1'}")
    private Participant participant;

    @Schema(description = "Сущность задачи", example = "{'id':'1',..., 'internship':{...}}")
    private Task task;

    @Schema(description = "Статус", example = "Pass")
    private String status;

    @Schema(description = "Комментарий", example = "Принято")
    private String comment;

    @Schema(description = "Просмотрено ли админом?", example = "true")
    private boolean checked;

    public static Performance toDomainObject(PerformanceDto performanceDto) {
        return new Performance(performanceDto.getId(), performanceDto.getParticipant(), performanceDto.getTask(),
                performanceDto.getStatus(), performanceDto.getComment(), performanceDto.isChecked());
    }

    public static PerformanceDto toDto(Performance performance) {
        return new PerformanceDto(performance.getId(), performance.getParticipant(), performance.getTask(),
                performance.getStatus(), performance.getComment(), performance.isChecked());
    }
}
