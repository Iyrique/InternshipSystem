package ds.rest.dto;

import ds.domain.Participant;
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
@Schema(description = "Сущность обучающегося")
public class ParticipantDto {

    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    @Schema(description = "Полное имя", example = "Воронежский Никита Максимович")
    private String fullName;

    @Schema(description = "Электронная почта", example = "voronezhskij_n_m@sc.vsu.ru")
    private String email;

    @Schema(description = "Номер телефона", example = "+79009009090")
    private String phoneNumber;

    @Schema(description = "Имя пользователя", example = "voronezhskij_n_m")
    private String username;

    @Schema(description = "Telegram", example = "@voronezhskij_n_m")
    private String telegramId;

    @Schema(description = "Допольнительная информация", example = "Аллергия на манго")
    private String about;

    @Schema(description = "Дата рождения", example = "01.01.01")
    private LocalDate dateOfBirth;

    @Schema(description = "Город", example = "Воронеж")
    private String city;

    @Schema(description = "Статус обучения", example = "Студент")
    private String educationStatus;

    @Schema(description = "Университет", example = "Воронежский государственный университет")
    private String university;

    @Schema(description = "Факультет", example = "Факультет компьютерных наук")
    private String faculty;

    @Schema(description = "Специализация", example = "Информационная безопасность")
    private String specialty;

    @Schema(description = "Курс", example = "3")
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
