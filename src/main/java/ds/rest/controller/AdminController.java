package ds.rest.controller;

import io.swagger.v3.oas.annotations.Hidden;
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

    /*
    Добавить:
    1. Публикация занятия lesson и распределение их по всем активным участникам
    2. Публикация задач task и распределение по всем участникам
    3. В случае неудачи, создать message для всех admin
    4. Проверка задач занятия. После запуска процесса в gitlab проверяется наличие свежих коммитов, и возвращается список содержащий:
    id задачи, название задачи, username_id, username, дата и время последнего коммита,
    автор последнего коммита, ссылка на коммит. Проверку необходимо осуществлять только у незасчитанных задач
    или у задач отправленных на доработку.
    5. Возможность оценить задачу (зачет, незачет, комм). После проверки создать message юзеру
    6. Создать ведомость по стажировке, все студенты и задачи, которые опубликованы на момент форм ведомости.
     */

    @GetMapping("/data")
    @Hidden
    public ResponseEntity<String> getAdminData() {
        return ResponseEntity.ok("Admin data");
    }
}
