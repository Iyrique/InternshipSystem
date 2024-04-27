package ds.rest.dto;

import ds.domain.Message;
import ds.domain.Participant;
import ds.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private Long id;
    private Participant participant;

    private Task task;

    private String message;

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
