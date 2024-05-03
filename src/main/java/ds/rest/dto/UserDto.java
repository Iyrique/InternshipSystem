package ds.rest.dto;

import ds.domain.Message;
import ds.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность пользователя")
public class UserDto {

    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    @Schema(description = "Имя пользователя", example = "voronezhskiy.nikita")
    private String username;

    @Schema(description = "Список сообщений", example = "[]")
    private List<Message> message;

    @Schema(description = "Роль", example = "ADMIN")
    private String role;

    public static User toDomainObject(UserDto userDto) {
        return new User(userDto.getId(), userDto.getUsername(), userDto.getMessage(), userDto.getRole());
    }

    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getMessage(), user.getRole());
    }
}
