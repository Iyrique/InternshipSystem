package ds.rest.dto;

import ds.domain.Participant;
import ds.domain.Performance;
import ds.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceDto {

    private Long id;
    private Participant participant;
    private Task task;

    private String status;
    private String comment;

    public static Performance toDomainObject(PerformanceDto performanceDto) {
        return new Performance(performanceDto.getId(), performanceDto.getParticipant(), performanceDto.getTask(),
                performanceDto.getStatus(), performanceDto.getComment());
    }

    public static PerformanceDto toDto(Performance performance) {
        return new PerformanceDto(performance.getId(), performance.getParticipant(), performance.getTask(),
                performance.getStatus(), performance.getComment());
    }
}
