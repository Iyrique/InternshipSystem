package ds.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin-controller", description = "Контроллер администратора")
public class AdminController {

    @GetMapping("/data")
    public ResponseEntity<String> getAdminData() {
        return ResponseEntity.ok("Admin data");
    }
}
