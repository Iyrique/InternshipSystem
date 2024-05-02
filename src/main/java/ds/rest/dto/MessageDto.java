package ds.rest.dto;

import ds.domain.Message;
import ds.domain.Participant;
import ds.domain.Task;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность сообщения")
public class MessageDto {

    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    @Schema(description = "Сущность ученика", example = "{'id':'1',..., 'course':'1'}")
    private Participant participant;

    @Schema(description = "Сущность задачи", example = "{'id':'1',..., 'internship':{...}}")
    private Task task;

    @Schema(description = "Сообщение", example = "Пример")
    private String message;

    @Schema(description = "Статус прочтения", example = "true")
    private boolean read;

    public static Message toDomainObject(MessageDto messageDto) {
        return new Message(messageDto.getId(), messageDto.getParticipant(), messageDto.getTask(),
                messageDto.getMessage(), messageDto.isRead());
    }

    public static MessageDto toDto(Message message) {
        return new MessageDto(message.getId(), message.getParticipant(), message.getTask(), message.getMessage(),
                message.isRead());
    }
}
