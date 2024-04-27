package ds.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "User-controller", description = "Пользовательское")
public class UserController {

    @GetMapping("/data")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getUserData(Authentication authentication) {
        return ResponseEntity.ok("User data");
    }

    @GetMapping("/settings")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String> getUserSettings(Authentication authentication) {
        return ResponseEntity.ok("User settings");
    }
}
