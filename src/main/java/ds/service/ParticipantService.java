package ds.service;

import ds.domain.Participant;

import java.util.List;

public interface ParticipantService {

    Participant saveParticipant(Participant participant);

    Participant updateParticipant(Participant participant);

    void deleteParticipant(Participant participant);

    List<Participant> getAllParticipants();

    List<Participant> findParticipantsByName(String name);
}
