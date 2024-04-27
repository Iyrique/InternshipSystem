package ds.rest.dto;

import ds.domain.Participant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto {

    private Long id;

    private String fullName;
    private String email;
    private String phoneNumber;
    private String username;
    private String telegramId;
    private String about;
    private LocalDate dateOfBirth;
    private String city;
    private String educationStatus;
    private String university;
    private String faculty;
    private String specialty;
    private int course;

    public static Participant toDomainObject(ParticipantDto participantDto) {
        return new Participant(participantDto.getId(), participantDto.getFullName(), participantDto.getEmail(),
                participantDto.getPhoneNumber(), participantDto.getUsername(), participantDto.getTelegramId(),
                participantDto.getAbout(), participantDto.getDateOfBirth(), participantDto.getCity(),
                participantDto.getEducationStatus(), participantDto.getUniversity(), participantDto.getFaculty(),
                participantDto.getSpecialty(), participantDto.getCourse());
    }

    public static ParticipantDto toDto(Participant participant) {
        return new ParticipantDto(participant.getId(), participant.getFullName(), participant.getEmail(),
                participant.getPhoneNumber(), participant.getUsername(), participant.getTelegramId(),
                participant.getAbout(), participant.getDateOfBirth(), participant.getCity(), participant.getEducationStatus(),
                participant.getUniversity(), participant.getFaculty(), participant.getSpecialty(), participant.getCourse());
    }
}
