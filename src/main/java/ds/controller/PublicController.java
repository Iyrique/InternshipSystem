package ds.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@Tag(name = "Public controller", description = "Публичный контроллер)))")
public class PublicController {

    @GetMapping("/data")
    public ResponseEntity<String> getPublicData() {
        return ResponseEntity.ok("Public data");
    }
}
