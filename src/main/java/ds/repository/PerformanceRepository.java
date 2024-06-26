package ds.repository;

import ds.domain.Participant;
import ds.domain.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long>{

    Performance getById(Long id);

    List<Performance> findAllByParticipant(Participant participant);

    Performance getByTaskIdAndParticipantId(Long taskId, Long participantId);
}
