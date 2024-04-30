package ds.repository;

import ds.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageRepositoryTest {

    @Mock
    private MessageRepository messageRepository;

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
    public void testFindAllByParticipant(){
        List<Message> messages = Arrays.asList(message1, message2);
        when(messageRepository.findAllByParticipant(message1.getParticipant())).thenReturn(messages);
        List<Message> result = messageRepository.findAllByParticipant(message1.getParticipant());
        assertEquals(messages, result);
    }

    @Test
    public void testFindAllByParticipantAndReadFalse() {
        List<Message> messages = Arrays.asList(message1, message2);
        when(messageRepository.findAllByParticipantAndReadFalse(message1.getParticipant())).thenReturn(messages);
        List<Message> result = messageRepository.findAllByParticipantAndReadFalse(message1.getParticipant());
        assertNotNull(result);
        assertEquals(messages, result);
    }

    @Test
    public void testGetById() {
        when(messageRepository.getById(1L)).thenReturn(message1);
        Message result = messageRepository.getById(1L);
        assertEquals(message1, result);
    }

}
