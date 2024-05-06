package ds.service;

import ds.domain.Participant;
import ds.domain.Performance;
import ds.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService{

    private final PerformanceRepository performanceRepository;

    @Override
    @Transactional
    public Performance savePerformance(Performance performance) {
        return performanceRepository.save(performance);
    }

    @Override
    @Transactional
    public Performance updatePerformance(Long id, Performance performance) {
        Performance existingPerformance = performanceRepository.getById(id);
        if (existingPerformance != null) {
            existingPerformance.setStatus(performance.getStatus());
            existingPerformance.setComment(performance.getComment());
            return performanceRepository.save(existingPerformance);
        }
        return null;
    }

    @Override
    @Transactional
    public void deletePerformance(Long id) {
        performanceRepository.deleteById(id);
    }

    @Override
    public List<Performance> getPerformancesByParticipant(Participant participant) {
        return performanceRepository.findAllByParticipant(participant);
    }

    public Performance getPerformanceByParticipantIdAndTaskId(Long participantId, Long taskId) {
        return performanceRepository.getByTaskIdAndParticipantId(taskId, participantId);
    }
}
