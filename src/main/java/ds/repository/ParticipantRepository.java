package ds.repository;

import ds.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Participant findByFullName(String name);

    Participant getById(Long id);
}
