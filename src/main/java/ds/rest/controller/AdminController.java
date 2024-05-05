package ds.rest.controller;

import ds.domain.*;
import ds.rest.dto.InternshipDto;
import ds.rest.dto.LessonDto;
import ds.rest.dto.TaskDto;
import ds.rest.dto.UserDto;
import ds.service.*;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin-controller", description = "Контроллер администратора")
public class AdminController {

    /*
    Добавить:
    5. Возможность оценить задачу (зачет, незачет, комм). После проверки создать message юзеру
    6. Создать ведомость по стажировке, все студенты и задачи, которые опубликованы на момент форм ведомости.
    7. Получение сообщения
     */

    private InternshipServiceImpl internshipService;
    private LessonServiceImpl lessonService;
    private TaskServiceImpl taskService;
    private UserServiceImpl userService;
    private ParticipantInternshipServiceImpl participantInternshipService;
    private PerformanceServiceImpl performanceService;

    @GetMapping("/data")
    @Hidden
    public ResponseEntity<String> getAdminData() {
        return ResponseEntity.ok("Admin data");
    }

    @PostMapping("/create/internship")
    @Operation(summary = "Создание стажировки", description = "Создание новой стажировки")
    public ResponseEntity<Internship> createInternship(@RequestBody @Parameter(description = "Сущность стажировки") InternshipDto internshipDto) {
        Internship newInternship = new Internship();
        newInternship.setName(internshipDto.getName());
        newInternship.setDescription(internshipDto.getDescription());
        newInternship.setStartDate(internshipDto.getStartDate());
        newInternship.setEndDate(internshipDto.getEndDate());
        newInternship.setEndDateRecording(internshipDto.getEndDateRecording());
        newInternship.setStatus(internshipDto.getStatus());
        Internship createdInternship = internshipService.createInternship(newInternship);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInternship);
    }

    @PostMapping("/create/lesson")
    @Operation(summary = "Создание урока", description = "Создание нового урока")
    public ResponseEntity<Lesson> createLesson(@RequestBody @Parameter(description = "Сущность урока") LessonDto lessonDto) {
        Lesson newLesson = new Lesson();
        newLesson.setName(lessonDto.getName());
        newLesson.setDescription(lessonDto.getDescription());
        newLesson.setDateTime(lessonDto.getDateTime());
        newLesson.setInternship(lessonDto.getInternship());
        Lesson createdLesson = lessonService.saveLesson(newLesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLesson);
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
    public ResponseEntity<Task> createTask(@RequestBody @Parameter(description = "Сущность задачи") TaskDto taskDto) {
        Task newTask = new Task();
        newTask.setName(taskDto.getName());
        newTask.setGitlabRepository(taskDto.getGitlabRepository());
        newTask.setInternship(taskDto.getInternship());
        Task createdTask = taskService.createTask(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
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
        for (ParticipantInternship participantInternship: participantInternships) {
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
    public ResponseEntity<User> createUser(@RequestBody @Parameter(description = "Сущность пользователя") UserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());
        newUser.setMessage(new ArrayList<>());
        newUser.setRole("ADMIN");
        User createdUser = userService.createUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
