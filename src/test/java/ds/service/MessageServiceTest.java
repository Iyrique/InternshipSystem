package ds.service;

import ds.domain.Internship;
import ds.domain.Message;
import ds.domain.Participant;
import ds.domain.Task;
import ds.repository.MessageRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    private static Message message1;
    private static Message message2;

    @BeforeAll
    public static void prepareTestData() {
        Participant participant = Participant.builder()
                .id(1L)
                .about("test")
                .city("test")
                .course(1)
                .dateOfBirth(LocalDate.of(2005, 1, 1))
                .educationStatus("test")
                .email("test")
                .faculty("test")
                .fullName("test")
                .phoneNumber("test")
                .telegramId("test")
                .university("test")
                .username("test")
                .build();

        Task task = Task.builder()
                .id(1L)
                .gitlabRepository("test")
                .name("test")
                .internship(Internship.builder()
                        .id(1L)
                        .description("Test")
                        .endDate(LocalDate.of(2024, 5, 10))
                        .endDateRecording(LocalDate.of(2024, 4, 26))
                        .name("IntTest")
                        .startDate(LocalDate.of(2024, 4, 27))
                        .status("ACTIVE")
                        .build())
                .build();

        message1 = Message.builder()
                .id(1L)
                .message("test")
                .participant(participant)
                .task(task)
                .read(true)
                .build();

        message2 = Message.builder()
                .id(2L)
                .message("test")
                .participant(participant)
                .task(task)
                .read(true)
                .build();
    }

    @Test
    public void testSaveMessage() {
        when(messageRepository.save(message1)).thenReturn(message1);
        Message savedMessage = messageService.saveMessage(message1);
        verify(messageRepository).save(message1);
        assertEquals(message1, savedMessage);
    }

    @Test
    public void testGetMessagesByParticipant() {
        Participant participant = message1.getParticipant();
        List<Message> messages = Arrays.asList(message1, message2);
        when(messageRepository.findAllByParticipant(participant)).thenReturn(messages);
        List<Message> result = messageService.getMessagesByParticipant(participant);
        assertEquals(result, messages);
    }

    @Test
    public void testGetUnreadMessagesByParticipant() {
        Participant participant = message1.getParticipant();
        List<Message> messages = Arrays.asList(message1, message2);
        when(messageRepository.findAllByParticipantAndReadFalse(participant)).thenReturn(messages);
        List<Message> result = messageService.getUnreadMessagesByParticipant(participant);
        assertEquals(messages, result);
    }

    @Test
    public void testMarkMessageAsRead() {
        Long messageId = 1L;
        Message message = new Message();
        message.setId(messageId);
        when(messageRepository.getById(messageId)).thenReturn(message);
        Message markedMessage = messageService.markMessageAsRead(messageId);
        assertEquals(message, markedMessage);
    }

    @Test
    public void testDeleteMessage() {
        messageService.deleteMessage(message1.getId());
        verify(messageRepository).deleteById(message1.getId());
    }

}
