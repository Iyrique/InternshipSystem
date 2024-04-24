package ds.service;

import ds.domain.Participant;
import ds.domain.Performance;

import java.util.List;

public interface PerformanceService {

    Performance savePerformance(Performance performance);

    Performance updatePerformance(Long id, Performance performance);

    void deletePerformance(Long id);

    List<Performance> getPerformancesByParticipant(Participant participant);
}
