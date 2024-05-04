package ds.rest.dto;

import ds.domain.Internship;
import ds.domain.Participant;
import ds.domain.ParticipantInternship;
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
@Schema(description = "Сущность связи обучающегося и стажировки")
public class ParticipantInternshipDto {

    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    @Schema(description = "Сущность ученика", example = "{'id':'1',..., 'course':'1'}")
    private Participant participant;

    @Schema(description = "Стажировка", example = "internship:{...}")
    private Internship internship;

    @Schema(description = "Дата регистрации", example = "01.01.21")
    private LocalDate registrationDate;

    @Schema(description = "Статус обучаемого", example = "ENDED")
    private String status;

    public static ParticipantInternship toDomainObject(ParticipantInternshipDto participantInternshipDto) {
        return new ParticipantInternship(participantInternshipDto.getId(), participantInternshipDto.getParticipant(),
                participantInternshipDto.getInternship(), participantInternshipDto.getRegistrationDate(),
                participantInternshipDto.getStatus());
    }

    public static ParticipantInternshipDto toDto(ParticipantInternship participantInternship) {
        return new ParticipantInternshipDto(participantInternship.getId(), participantInternship.getParticipant(),
                participantInternship.getInternship(), participantInternship.getRegistrationDate(), participantInternship.getStatus());
    }
}
