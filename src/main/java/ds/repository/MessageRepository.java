package ds.repository;

import ds.domain.Message;
import ds.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByParticipant(Participant participant);

    List<Message> findAllByParticipantAndReadFalse(Participant participant);

    Message getById(Long id);
}
