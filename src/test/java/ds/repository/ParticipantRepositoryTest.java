package ds.repository;

import ds.domain.Participant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ParticipantRepositoryTest {

    @Mock
    private ParticipantRepository participantRepository;

    private Participant participant;

    @BeforeEach
    public void prepareTestData() {
        participant = Participant.builder()
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
    }

    @Test
    public void testFindByFullName() {
        String fullName = "test";
        when(participantRepository.findByFullName(fullName)).thenReturn(participant);
        Participant result = participantRepository.findByFullName(fullName);
        assertEquals(result, participant);
    }

    @Test
    public void testGetById() {
        when(participantRepository.getById(1L)).thenReturn(participant);
        Participant result = participantRepository.getById(1L);
        assertEquals(result, participant);
    }


}
