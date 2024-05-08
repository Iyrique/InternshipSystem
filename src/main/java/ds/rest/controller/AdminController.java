package ds.rest.controller;

import ds.domain.*;
import ds.rest.dto.*;
import ds.service.*;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ADMIN', 'ROOT')")
@Tag(name = "Admin-controller", description = "Контроллер администратора")
@RequiredArgsConstructor
public class AdminController {

    private final InternshipServiceImpl internshipService;
    private final LessonServiceImpl lessonService;
    private final TaskServiceImpl taskService;
    private final UserServiceImpl userService;
    private final ParticipantInternshipServiceImpl participantInternshipService;
    private final PerformanceServiceImpl performanceService;
    private final ParticipantServiceImpl participantService;
    private final MessageServiceImpl messageService;

    @GetMapping("/data")
    @Hidden
    public ResponseEntity<String> getAdminData() {
        return ResponseEntity.ok("Admin data");
    }

    @PostMapping("/create/internship")
    @Operation(summary = "Создание стажировки", description = "Создание новой стажировки")
    public ResponseEntity<InternshipDto> createInternship(@RequestBody @Parameter(description = "Сущность стажировки") InternshipDto internshipDto) {
        Internship newInternship = new Internship();
        newInternship.setName(internshipDto.getName());
        newInternship.setDescription(internshipDto.getDescription());
        newInternship.setStartDate(internshipDto.getStartDate());
        newInternship.setEndDate(internshipDto.getEndDate());
        newInternship.setEndDateRecording(internshipDto.getEndDateRecording());
        newInternship.setStatus(internshipDto.getStatus());
        Internship createdInternship = internshipService.createInternship(newInternship);
        return ResponseEntity.status(HttpStatus.CREATED).body(InternshipDto.toDto(createdInternship));
    }

    @PostMapping("/create/lesson")
    @Operation(summary = "Создание урока", description = "Создание нового урока")
    public ResponseEntity<LessonDto> createLesson(@RequestBody @Parameter(description = "Сущность урока") LessonDto lessonDto) {
        Lesson newLesson = new Lesson();
        newLesson.setName(lessonDto.getName());
        newLesson.setDescription(lessonDto.getDescription());
        newLesson.setDateTime(lessonDto.getDateTime());
        newLesson.setInternship(lessonDto.getInternship());
        Lesson createdLesson = lessonService.saveLesson(newLesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(LessonDto.toDto(createdLesson));
    }

    @PostMapping("/publish/{internshipId}/lesson/{lessonId}")
    @Operation(summary = "Публикация занятия", description = "Распределение занятия по всем активным участникам")
    public ResponseEntity<String> publishLesson(
            @PathVariable @Parameter(description = "Идентификатор стажировки") Long internshipId,
            @PathVariable @Parameter(description = "Идентификатор урока") Long lessonId) {

        Internship internship = internshipService.getInternshipById(internshipId);
        if (internship == null) {
            return ResponseEntity.badRequest().body("Стажировка не найдена с id: " + internshipId);
        }

        Lesson exLesson = lessonService.getLessonById(lessonId);
        if (exLesson == null) {
            return ResponseEntity.badRequest().body("Урок не найден с id: " + lessonId);
        }

        exLesson.setInternship(internship);

        Lesson result = lessonService.saveLesson(exLesson);

        if (result != null) {
            return ResponseEntity.ok("Занятие успешно опубликовано и распределено по участникам.");
        } else {
            String messageText = "Не удалось сохранить урок.";
            userService.createAndSendAdminMessage(messageText);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageText);
        }
    }

    @PostMapping("/create/task")
    @Operation(summary = "Создание задачи", description = "Создание новой задачи")
    public ResponseEntity<TaskDto> createTask(@RequestBody @Parameter(description = "Сущность задачи") TaskDto taskDto) {
        Task newTask = new Task();
        newTask.setName(taskDto.getName());
        newTask.setGitlabRepository(taskDto.getGitlabRepository());
        newTask.setInternship(taskDto.getInternship());
        Task createdTask = taskService.createTask(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(TaskDto.toDto(createdTask));
    }

    @PostMapping("/publish/{internshipId}/task/{taskId}")
    @Operation(summary = "Публикация задачи", description = "Распределение задачи по всем активным участникам")
    public ResponseEntity<String> publishTask(
            @PathVariable @Parameter(description = "Идентификатор стажировки") Long internshipId,
            @PathVariable @Parameter(description = "Идентификатор задачи") Long taskId) {

        Internship internship = internshipService.getInternshipById(internshipId);
        if (internship == null) {
            return ResponseEntity.badRequest().body("Не удалось найти стажировку с id: " + internshipId);
        }

        Task task = taskService.getTaskById(taskId);
        if (task == null) {
            return ResponseEntity.badRequest().body("Не удалось найти задачу с id: " + taskId);
        }

        List<ParticipantInternship> participantInternships = participantInternshipService.getByInternship(internship);
        for (ParticipantInternship participantInternship : participantInternships) {
            Performance performance = Performance.builder()
                    .task(task)
                    .participant(participantInternship.getParticipant())
                    .status("NoPass")
                    .comment("")
                    .checked(true)
                    .build();
            performanceService.savePerformance(performance);
        }

        return ResponseEntity.ok("Задача успешно опубликована и распределена по всем участникам.");
    }

    @PostMapping("/create-admin")
    @Operation(summary = "Создание админа", description = "Создание нового админа")
    public ResponseEntity<UserDto> createUser(@RequestBody @Parameter(description = "Сущность пользователя") UserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());
        newUser.setMessage(new ArrayList<>());
        newUser.setRole("ADMIN");
        User createdUser = userService.createUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserDto.toDto(createdUser));
    }

    @PostMapping("/evaluate/task/{internshipId}/{participantId}/{taskId}")
    @Operation(summary = "Оценка задачи", description = "Оценка задачи и отправка сообщения пользователю")
    public ResponseEntity<String> evaluateTask(
            @PathVariable @Parameter(description = "Идентификатор стажировки") Long internshipId,
            @PathVariable @Parameter(description = "Идентификатор обучающегося") Long participantId,
            @PathVariable @Parameter(description = "Идентификатор задачи") Long taskId,
            @RequestParam @Parameter(description = "Оценка задачи (Pass / NoPass)") String evaluation,
            @RequestParam @Parameter(description = "Комментарий к оценке") String comment) {

        Internship internship = internshipService.getInternshipById(internshipId);
        if (internship == null) {
            return ResponseEntity.badRequest().body("Не удалось найти стажировку с id: " + internshipId);
        }

        Participant participant = participantService.getParticipantById(participantId);

        if (participant == null) {
            return ResponseEntity.badRequest().body("Не удалось найти участника с id: " + participantId);
        }

        ParticipantInternship participantInternship = participantInternshipService.getByParticipantAndInternship(participant, internship);

        if (participantInternship == null) {
            return ResponseEntity.badRequest().body("Такой участник не зарегистрирован на эту стажировку");
        }

        Task task = taskService.getTaskById(taskId);
        if (task == null) {
            return ResponseEntity.badRequest().body("Не удалось найти задачу с id: " + taskId);
        }

        Performance performance = performanceService.getPerformanceByParticipantIdAndTaskId(participantId, taskId);
        if (performance == null) {
            return ResponseEntity.badRequest().body("Успеваемость ученика по задаче не найдена");
        }
        performance.setStatus(evaluation);
        performance.setComment(comment);
        performanceService.updatePerformance(performance.getId(), performance);

        String messageContent = "Ваша задача \"" + task.getName() + "\" была оценена как: " + evaluation +
                ". Комментарий: " + comment;
        userService.sendMessageByUsername(participant.getUsername(), messageContent);
        return ResponseEntity.ok("Задача успешно оценена. Сообщение отправлено пользователю.");
    }

    @GetMapping("/messages/{messageId}")
    @Operation(summary = "Получение сообщения", description = "Получение сообщения по его идентификатору")
    public ResponseEntity<MessageDto> getMessageById(@PathVariable @Parameter(description = "Идентификатор сообщения") Long messageId) {
        Message message = messageService.getMessageById(messageId);
        if (message != null) {
            return ResponseEntity.ok(MessageDto.toDto(message));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/messages/{username}")
    @Operation(summary = "Получение сообщений пользователя", description = "Получение сообщения по его идентификатору")
    public ResponseEntity<List<MessageDto>> getMessageById(@PathVariable @Parameter(description = "Имя пользователя") String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.getMessage().stream().map(MessageDto::toDto).collect(Collectors.toList()));

    }

    @PostMapping("/generate/report/{internshipId}")
    @Operation(summary = "Генерация ведомости", description = "Создание ведомости по активным участникам стажировки и задачам")
    public ResponseEntity<String> generateReport(@PathVariable @Parameter(description = "Идентификатор стажировки") Long internshipId) {
        Internship internship = internshipService.getInternshipById(internshipId);
        if (internship == null) {
            return ResponseEntity.badRequest().body("Не удалось найти стажировку с id: " + internshipId);
        }

        List<ParticipantInternship> participantInternships = participantInternshipService.findByStatusAndInternship("ACTIVE", internship);

        List<Task> tasks = taskService.getTasksByInternship(internship.getId());

        StringBuilder report = new StringBuilder();
        report.append("Ведомость для стажировки: ").append(internship.getName()).append("\n\n");
        report.append("Участник\t\t");
        for (Task task : tasks) {
            report.append(task.getName()).append("\t\t");
        }
        report.append("\n");

        for (ParticipantInternship participantInternship : participantInternships) {
            report.append(participantInternship.getParticipant().getFullName()).append("\t\t");
            for (Task task : tasks) {
                Performance performance = performanceService.getPerformanceByParticipantIdAndTaskId(participantInternship.getParticipant().getId(), task.getId());
                if (performance != null && performance.getStatus().equals("Pass")) {
                    report.append("Pass\t\t");
                } else {
                    report.append("NoPass\t\t");
                }
            }
            report.append("\n");
        }
        return ResponseEntity.ok(report.toString());
    }

}
