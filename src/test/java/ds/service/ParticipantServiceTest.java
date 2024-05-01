package ds.service;

import ds.domain.Participant;
import ds.repository.ParticipantRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParticipantServiceTest {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantServiceImpl participantService;

    private static Participant participant;

    @BeforeAll
    public static void prepareTestData() {
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
    void testSaveParticipant() {
        when(participantRepository.save(participant)).thenReturn(participant);
        Participant savedParticipant = participantService.saveParticipant(participant);

        assertNotNull(savedParticipant);
        assertEquals(participant.getId(), savedParticipant.getId());
        assertEquals(participant.getFullName(), savedParticipant.getFullName());
        assertEquals(participant.getEmail(), savedParticipant.getEmail());
    }

    @Test
    void testUpdateParticipant() {
        when(participantRepository.getById(participant.getId())).thenReturn(participant);
        Participant updatedParticipant = participantService.updateParticipant(participant.getId(), participant);
        assertNotNull(updatedParticipant);
        assertEquals(participant.getId(), updatedParticipant.getId());
        assertEquals(participant.getFullName(), updatedParticipant.getFullName());
        assertEquals(participant.getEmail(), updatedParticipant.getEmail());
    }

    @Test
    void testDeleteParticipant() {
        participantService.deleteParticipant(participant.getId());
        verify(participantRepository).deleteById(participant.getId());
    }

    @Test
    void testGetAllParticipants() {
        List<Participant> participants = Arrays.asList(participant);
        when(participantRepository.findAll()).thenReturn(participants);
        List<Participant> retrievedParticipants = participantService.getAllParticipants();
        assertNotNull(retrievedParticipants);
        assertEquals(1, retrievedParticipants.size());
        assertEquals(participant.getId(), retrievedParticipants.get(0).getId());
    }

    @Test
    void testFindParticipantByName() {
        when(participantRepository.findByFullName(participant.getFullName())).thenReturn(participant);
        Participant retrievedParticipant = participantService.findParticipantByName(participant.getFullName());
        assertNotNull(retrievedParticipant);
        assertEquals(participant.getId(), retrievedParticipant.getId());
        assertEquals(participant.getFullName(), retrievedParticipant.getFullName());
    }

}
