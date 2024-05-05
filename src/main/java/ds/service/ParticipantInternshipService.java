package ds.service;

import ds.domain.Internship;
import ds.domain.Participant;
import ds.domain.ParticipantInternship;

import java.util.List;

public interface ParticipantInternshipService {

    List<ParticipantInternship> getByParticipant(Participant participant);

    List<ParticipantInternship> getByInternship(Internship internship);

    ParticipantInternship getByParticipantAndInternship(Participant participant, Internship internship);

    ParticipantInternship save(ParticipantInternship participantInternship);

    void delete(Long id);

    ParticipantInternship updateStatus(Long id, String newStatus);

    ParticipantInternship getById(Long id);

    List<ParticipantInternship> findByStatusAndInternship(String status, Internship internship);
}
