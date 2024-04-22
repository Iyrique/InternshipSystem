package ds.service;

import ds.domain.Message;
import ds.domain.Participant;

import java.util.List;

public interface MessageService {

    void saveMessage();

    List<Message> getMessagesByParticipant(Participant participant);

    List<Message> getUnreadMessagesByParticipant(Participant participant);

    Message markMessageAsRead(Long messageId);

    void deleteMessage(Long messageId);
}
