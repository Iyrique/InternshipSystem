package ds.service;

import ds.domain.Internship;
import ds.domain.Participant;
import ds.domain.ParticipantInternship;
import ds.repository.ParticipantInternshipRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParticipantRepositoryServiceTest {

    @Mock
    private ParticipantInternshipRepository participantInternshipRepository;

    @InjectMocks
    private ParticipantInternshipServiceImpl participantInternshipService;

    @Test
    void getByParticipantTest() {
        Participant participant = new Participant();
        List<ParticipantInternship> expectedList = new ArrayList<>();
        when(participantInternshipRepository.findByParticipant(participant)).thenReturn(expectedList);
        List<ParticipantInternship> resultList = participantInternshipService.getByParticipant(participant);
        assertEquals(expectedList, resultList);
        verify(participantInternshipRepository, times(1)).findByParticipant(participant);
    }

    @Test
    void getByInternshipTest() {
        Internship internship = new Internship();
        List<ParticipantInternship> expectedList = new ArrayList<>();
        when(participantInternshipRepository.findByInternship(internship)).thenReturn(expectedList);
        List<ParticipantInternship> resultList = participantInternshipService.getByInternship(internship);
        assertEquals(expectedList, resultList);
        verify(participantInternshipRepository, times(1)).findByInternship(internship);
    }

    @Test
    void updateStatusTest() {
        Long id = 1L;
        String newStatus = "Enrolled";
        ParticipantInternship participantInternship = new ParticipantInternship();
        when(participantInternshipRepository.findById(id)).thenReturn(Optional.of(participantInternship));
        when(participantInternshipRepository.save(participantInternship)).thenReturn(participantInternship);
        ParticipantInternship updatedParticipantInternship = participantInternshipService.updateStatus(id, newStatus);
        assertNotNull(updatedParticipantInternship);
        assertEquals(newStatus, updatedParticipantInternship.getStatus());
        verify(participantInternshipRepository, times(1)).findById(id);
        verify(participantInternshipRepository, times(1)).save(participantInternship);
    }

    @Test
    void getByIdTest() {
        Long id = 1L;
        ParticipantInternship participantInternship = new ParticipantInternship();
        when(participantInternshipRepository.findById(id)).thenReturn(Optional.of(participantInternship));
        ParticipantInternship result = participantInternshipService.getById(id);
        assertNotNull(result);
        assertEquals(participantInternship, result);
        verify(participantInternshipRepository, times(1)).findById(id);
    }

    @Test
    void saveTest() {
        ParticipantInternship participantInternship = new ParticipantInternship();
        when(participantInternshipRepository.save(participantInternship)).thenReturn(participantInternship);
        ParticipantInternship savedParticipantInternship = participantInternshipService.save(participantInternship);
        assertNotNull(savedParticipantInternship);
        assertEquals(participantInternship, savedParticipantInternship);
        verify(participantInternshipRepository, times(1)).save(participantInternship);
    }

    @Test
    void deleteTest() {
        Long id = 1L;
        participantInternshipService.delete(id);
        verify(participantInternshipRepository, times(1)).deleteById(id);
    }
}
