package ds.rest.dto;

import ds.domain.Internship;
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
@Schema(description = "Сущность стажировки")
public class InternshipDto {

    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    @Schema(description = "Название стажировки", example = "Java 2024 Digital Spirit")
    private String name;

    @Schema(description = "Описание стажировки", example = "Стажировка по Java, которая продлится 2 месяца и будет включать" +
            "в себя ...")
    private String description;

    @Schema(description = "Дата старта стажировки", example = "02.05.2024")
    private LocalDate startDate;

    @Schema(description = "Дата окончания стажировки", example = "10.05.2024")
    private LocalDate endDate;

    @Schema(description = "Дата окончания записи на стажировку", example = "01.05.2024")
    private LocalDate endDateRecording;

    @Schema(description = "Статус стажировки", example = "ACTIVE")
    private String status;

    public static Internship toDomainObject(InternshipDto internshipDto) {
        return new Internship(internshipDto.getId(), internshipDto.getName(), internshipDto.getDescription(),
                internshipDto.getStartDate(), internshipDto.getEndDate(), internshipDto.getEndDateRecording(),
                internshipDto.getStatus());
    }

    public static InternshipDto toDto(Internship internship) {
        return new InternshipDto(internship.getId(), internship.getName(), internship.getDescription(),
                internship.getStartDate(), internship.getEndDate(), internship.getEndDateRecording(),
                internship.getStatus());
    }
}
