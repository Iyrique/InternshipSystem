package ds.repository;


import ds.domain.Internship;
import ds.domain.Participant;
import ds.domain.ParticipantInternship;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParticipantInternshipRepositoryTest {

    @Mock
    private ParticipantInternshipRepository participantInternshipRepository;

    @Test
    public void testFindByParticipant() {
        Participant participant = new Participant();
        participant.setId(1L);
        List<ParticipantInternship> participantInternships = new ArrayList<>();
        participantInternships.add(new ParticipantInternship());
        participantInternships.add(new ParticipantInternship());
        when(participantInternshipRepository.findByParticipant(participant)).thenReturn(participantInternships);
        List<ParticipantInternship> result = participantInternshipRepository.findByParticipant(participant);
        assertEquals(participantInternships.size(), result.size());
        verify(participantInternshipRepository, times(1)).findByParticipant(participant);
    }

    @Test
    public void testFindByInternship() {
        Internship internship = new Internship();
        internship.setId(1L);
        List<ParticipantInternship> participantInternships = new ArrayList<>();
        participantInternships.add(new ParticipantInternship());
        participantInternships.add(new ParticipantInternship());
        when(participantInternshipRepository.findByInternship(internship)).thenReturn(participantInternships);
        List<ParticipantInternship> result = participantInternshipRepository.findByInternship(internship);
        assertEquals(participantInternships.size(), result.size());
        verify(participantInternshipRepository, times(1)).findByInternship(internship);
    }

    @Test
    public void testGetByParticipantAndInternship() {
        Participant participant = new Participant();
        participant.setId(1L);
        Internship internship = new Internship();
        internship.setId(1L);
        ParticipantInternship participantInternship = new ParticipantInternship();
        when(participantInternshipRepository.getByParticipantAndInternship(participant, internship))
                .thenReturn(participantInternship);
        ParticipantInternship result = participantInternshipRepository.getByParticipantAndInternship(participant, internship);
        assertEquals(participantInternship, result);
        verify(participantInternshipRepository, times(1)).getByParticipantAndInternship(participant, internship);
    }

}
