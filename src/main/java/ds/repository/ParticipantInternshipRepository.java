package ds.repository;

import ds.domain.Internship;
import ds.domain.Participant;
import ds.domain.ParticipantInternship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantInternshipRepository extends JpaRepository<ParticipantInternship, Long> {

    List<ParticipantInternship> findByParticipant(Participant participant);

    List<ParticipantInternship> findByInternship(Internship internship);

    ParticipantInternship getByParticipantAndInternship(Participant participant, Internship internship);

    List<ParticipantInternship> findByStatusAndInternship(String status, Internship internship);
}
