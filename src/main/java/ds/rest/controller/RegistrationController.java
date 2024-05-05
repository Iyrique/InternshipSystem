package ds.rest.controller;

import ds.domain.Internship;
import ds.domain.Participant;
import ds.domain.ParticipantInternship;
import ds.domain.User;
import ds.rest.dto.ParticipantDto;
import ds.service.InternshipServiceImpl;
import ds.service.ParticipantInternshipService;
import ds.service.ParticipantServiceImpl;
import ds.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/register")
@Tag(name = "Register-controller", description = "Контроллер регистрации")
public class RegistrationController {

    private InternshipServiceImpl internshipService;

    private ParticipantServiceImpl participantService;

    private ParticipantInternshipService participantInternshipService;

    private UserServiceImpl userService;

    @PostMapping("/{internshipId}")
    @Operation(summary = "Регистрация на стажировку", description = "Позволяет зарегистрироваться на конкретную стажировку")
    public ResponseEntity<String> registerParticipantForInternship(
            @PathVariable("internshipId")
            @Parameter(description = "Идентификатор стажировки")
                    Long internshipId,
            @RequestBody @Parameter(description = "Учащийся и его данные") ParticipantDto participantDto) {

        Internship internship = internshipService.getInternshipById(internshipId);
        if (internship != null) {
            return ResponseEntity.badRequest().body("Стажировка не найдена с id: " + internshipId);
        }
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(internship.getEndDateRecording())) {
            return ResponseEntity.badRequest().body("Регистрация закрыта.");
        }

        Participant participant = participantChecker(participantDto);

        return enrollInternship(participant, internship);
    }

    private Participant participantChecker(ParticipantDto participantDto) {
        Participant participant = participantService.findParticipantByName(participantDto.getFullName());
        if (participant == null) {
            participant = Participant.builder()
                    .fullName(participantDto.getFullName())
                    .email(participantDto.getEmail())
                    .phoneNumber(participantDto.getPhoneNumber())
                    .username(participantDto.getUsername())
                    .telegramId(participantDto.getTelegramId())
                    .dateOfBirth(participantDto.getDateOfBirth())
                    .city(participantDto.getCity())
                    .educationStatus(participantDto.getEducationStatus())
                    .university(participantDto.getUniversity())
                    .faculty(participantDto.getFaculty())
                    .specialty(participantDto.getSpecialty())
                    .course(participantDto.getCourse())
                    .build();
            participant = participantService.saveParticipant(participant);
        } else {
            participant = participantService.updateParticipant(participant.getId(),
                    ParticipantDto.toDomainObject(participantDto));
        }
        return participant;
    }

    private ResponseEntity<String> enrollInternship(Participant participant, Internship internship) {
        ParticipantInternship participantInternship = participantInternshipService.getByParticipantAndInternship(participant, internship);
        if (participantInternship == null) {
            participantInternship = new ParticipantInternship();
            participantInternship.setParticipant(participant);
            participantInternship.setInternship(internship);
            participantInternship.setStatus("Enrolled");
            participantInternship = participantInternshipService.save(participantInternship);
            User user = createUser(participant);
            return ResponseEntity.ok("Успешно! Зарегистрирован пользователь: " + user.getUsername() + ":" + user.getPassword());
        } else {
            return ResponseEntity.ok("Вы уже записаны на эту стажировку!");
        }
    }

    private User createUser(Participant participant) {
        User user = User.builder()
                .username(participant.getUsername())
                .password("password")
                .role("USER")
                .message(new ArrayList<>())
                .build();
        return userService.createUser(user);
    }

}
