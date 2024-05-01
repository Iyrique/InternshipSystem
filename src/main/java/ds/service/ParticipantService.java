package ds.service;

import ds.domain.Participant;

import java.util.List;

public interface ParticipantService {

    Participant saveParticipant(Participant participant);

    Participant updateParticipant(Long id, Participant participant);

    void deleteParticipant(Long participantId);

    List<Participant> getAllParticipants();

    Participant findParticipantByName(String name);
}
