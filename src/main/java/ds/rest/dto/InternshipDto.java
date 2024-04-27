package ds.rest.dto;

import ds.domain.Internship;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternshipDto {

    private Long id;

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate endDateRecording;
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
