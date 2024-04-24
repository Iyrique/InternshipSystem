package ds.service;

import ds.domain.Message;
import ds.domain.Participant;
import ds.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;

    @Override
    @Transactional
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesByParticipant(Participant participant) {
        return messageRepository.findAllByParticipant(participant);
    }

    @Override
    public List<Message> getUnreadMessagesByParticipant(Participant participant) {
        return messageRepository.findAllByParticipantAndReadFalse(participant);
    }

    @Override
    @Transactional
    public Message markMessageAsRead(Long messageId) {
        Message message = messageRepository.getById(messageId);
        if (message != null) {
            message.setRead(true);
            return messageRepository.save(message);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteMessage(Long messageId) {
        messageRepository.deleteById(messageId);
    }
}
