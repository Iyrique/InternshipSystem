package ds.rest.controller;

import ds.domain.Internship;
import ds.rest.dto.InternshipDto;
import ds.service.InternshipServiceImpl;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/public")
@Tag(name = "Public controller", description = "Публичный контроллер. Информация, доступная каждому")
public class PublicController {

    @Autowired
    private InternshipServiceImpl internshipService;

    @GetMapping("/data")
    @Hidden
    @Operation(summary = "Тестовый контроллер", description = "Тестовый контроллер для проверки работоспособности")
    public ResponseEntity<String> getPublicData() {
        return ResponseEntity.ok("Public data");
    }

    @GetMapping("/internships")
    @Operation(summary = "Получение списка всех стажировок", description = "Возврашает данные о всех стажировках компании")
    public ResponseEntity<List<InternshipDto>> getAllInternships() {
        List<InternshipDto> internships = internshipService.getAllInternships()
                .stream()
                .map(InternshipDto::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(internships, HttpStatus.OK);
    }

    @GetMapping("/internships/{id}")
    @Operation(summary = "Получение стажировки по id", description = "Возврашает данные о выбранной стажировке компании")
    public ResponseEntity<InternshipDto> getInternshipById(@PathVariable @Parameter(description = "Идентификатор стажировки") Long id) {
        Internship internship = internshipService.getInternshipById(id);
        return new ResponseEntity<>(InternshipDto.toDto(internship), HttpStatus.OK);
    }

    @GetMapping("/internships/active")
    @Operation(summary = "Получение списка активных стажировок", description = "Возврашает данные о всех активных стажировках компании")
    public ResponseEntity<List<InternshipDto>> getActiveInternships() {
        List<InternshipDto> internships = internshipService.getInternshipsByStatus("ACTIVE")
                .stream()
                .map(InternshipDto::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(internships, HttpStatus.OK);
    }

    @GetMapping("/internships/ended")
    @Operation(summary = "Получение списка окончившихся стажировок", description = "Возврашает данные о всех окончившихся стажировках компании")
    public ResponseEntity<List<InternshipDto>> getEndedInternships() {
        List<InternshipDto> internships = internshipService.getInternshipsByStatus("ENDED")
                .stream()
                .map(InternshipDto::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(internships, HttpStatus.OK);
    }

}
