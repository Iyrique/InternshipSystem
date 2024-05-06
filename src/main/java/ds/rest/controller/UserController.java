package ds.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('USER')")
@Tag(name = "User-controller", description = "Пользовательское")
@RequiredArgsConstructor
public class UserController {

    /*
    Добавить:
    1. Возможность просмотра ведомости
    2. Получение сообщения
     */

    @GetMapping("/data")
    public ResponseEntity<String> getUserData() {
        return ResponseEntity.ok("User data");
    }

    @GetMapping("/settings")
    public ResponseEntity<String> getUserSettings() {
        return ResponseEntity.ok("User settings");
    }
}
