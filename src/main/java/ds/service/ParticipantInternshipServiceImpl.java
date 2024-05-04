package ds.service;

import ds.domain.Internship;
import ds.domain.Participant;
import ds.domain.ParticipantInternship;
import ds.repository.ParticipantInternshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantInternshipServiceImpl implements ParticipantInternshipService {

    private final ParticipantInternshipRepository participantInternshipRepository;

    @Override
    public List<ParticipantInternship> getByParticipant(Participant participant) {
        return participantInternshipRepository.findByParticipant(participant);
    }

    @Override
    public List<ParticipantInternship> getByInternship(Internship internship) {
        return participantInternshipRepository.findByInternship(internship);
    }

    @Override
    public ParticipantInternship getByParticipantAndInternship(Participant participant, Internship internship) {
        return participantInternshipRepository.getByParticipantAndInternship(participant, internship);
    }

    @Override
    @Transactional
    public ParticipantInternship save(ParticipantInternship participantInternship) {
        return participantInternshipRepository.save(participantInternship);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        participantInternshipRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ParticipantInternship updateStatus(Long id, String newStatus) {
        ParticipantInternship participantInternship = participantInternshipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ParticipantInternship not found with id: " + id));
        participantInternship.setStatus(newStatus);
        return participantInternshipRepository.save(participantInternship);
    }

    @Override
    public ParticipantInternship getById(Long id) {
        return participantInternshipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ParticipantInternship not found with id: " + id));
    }
}
