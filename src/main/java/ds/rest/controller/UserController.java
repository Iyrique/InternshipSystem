package ds.rest.controller;

import ds.domain.User;
import ds.rest.dto.MessageDto;
import ds.service.MessageServiceImpl;
import ds.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('USER')")
@Tag(name = "User-controller", description = "Пользовательское")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/messages/{username}")
    @Operation(summary = "Получение сообщений пользователя", description = "Получение сообщения по его идентификатору")
    public ResponseEntity<List<MessageDto>> getMessageById(@PathVariable @Parameter(description = "Имя пользователя") String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.getMessage().stream().map(MessageDto::toDto).collect(Collectors.toList()));

    }

    @GetMapping("/data")
    public ResponseEntity<String> getUserData() {
        return ResponseEntity.ok("User data");
    }

    @GetMapping("/settings")
    public ResponseEntity<String> getUserSettings() {
        return ResponseEntity.ok("User settings");
    }
}
