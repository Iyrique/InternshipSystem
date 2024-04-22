package ds.service;

import ds.domain.Participant;
import ds.domain.Performance;

import java.util.List;

public interface PerformanceService {

    void savePerformance(Performance performance);

    void updatePerformance(Long id, Performance performance);

    void deletePerformance(Long id);

    List<Performance> getPerformancesByParticipant(Participant participant);
}
