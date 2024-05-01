package ds.service;

import ds.domain.Participant;
import ds.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService{

    private final ParticipantRepository participantRepository;

    @Override
    @Transactional
    public Participant saveParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    @Transactional
    public Participant updateParticipant(Long id, Participant participant) {
        Participant existingParticipant = participantRepository.getById(id);
        if (existingParticipant != null) {
            existingParticipant.setFullName(participant.getFullName());
            existingParticipant.setEmail(participant.getEmail());
            existingParticipant.setPhoneNumber(participant.getPhoneNumber());
            existingParticipant.setUsername(participant.getUsername());
            existingParticipant.setTelegramId(participant.getTelegramId());
            existingParticipant.setAbout(participant.getAbout());
            existingParticipant.setCity(participant.getCity());
            existingParticipant.setEducationStatus(participant.getEducationStatus());
            existingParticipant.setUniversity(participant.getUniversity());
            existingParticipant.setFaculty(participant.getFaculty());
            existingParticipant.setSpecialty(participant.getSpecialty());
            existingParticipant.setCourse(participant.getCourse());
            return participantRepository.save(existingParticipant);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteParticipant(Long participantId) {
        participantRepository.deleteById(participantId);
    }

    @Override
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @Override
    public Participant findParticipantByName(String name) {
        return participantRepository.findByFullName(name);
    }
}
