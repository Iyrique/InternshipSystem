package ds.service;


import ds.domain.Internship;
import ds.domain.Participant;
import ds.domain.Performance;
import ds.domain.Task;
import ds.repository.PerformanceRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PerformanceServiceTest {

    @Mock
    private PerformanceRepository performanceRepository;

    @InjectMocks
    private PerformanceServiceImpl performanceService;

    private static Performance performance;

    @BeforeAll
    public static void prepareTestData() {
        performance = Performance.builder()
                .id(1L)
                .comment("test")
                .status("test")
                .participant(Participant.builder()
                        .id(1L)
                        .about("test")
                        .city("test")
                        .course(1)
                        .dateOfBirth(LocalDate.of(2005, 1, 1))
                        .educationStatus("test")
                        .email("test")
                        .faculty("test")
                        .fullName("test")
                        .phoneNumber("test")
                        .telegramId("test")
                        .university("test")
                        .username("test")
                        .build())
                .task(Task.builder()
                        .id(1L)
                        .gitlabRepository("test")
                        .name("test")
                        .internship(Internship.builder()
                                .id(1L)
                                .description("Test")
                                .endDate(LocalDate.of(2024, 5, 10))
                                .endDateRecording(LocalDate.of(2024, 4, 26))
                                .name("IntTest")
                                .startDate(LocalDate.of(2024, 4, 27))
                                .status("ACTIVE")
                                .build())
                        .build())
                .build();
    }

    @Test
    public void testSavePerformance() {
        when(performanceRepository.save(performance)).thenReturn(performance);
        Performance savedPerformance = performanceService.savePerformance(performance);
        assertNotNull(savedPerformance);
        verify(performanceRepository, times(1)).save(performance);
    }

    @Test
    public void testUpdatePerformance() {
        Long id = 1L;
        Performance performance = new Performance();
        performance.setId(id);

        Performance existingPerformance = new Performance();
        existingPerformance.setId(id);

        when(performanceRepository.getById(id)).thenReturn(existingPerformance);
        when(performanceRepository.save(existingPerformance)).thenReturn(existingPerformance);

        Performance updatedPerformance = performanceService.updatePerformance(id, performance);

        assertNotNull(updatedPerformance);
        assertEquals(performance.getId(), updatedPerformance.getId());
        verify(performanceRepository, times(1)).getById(id);
        verify(performanceRepository, times(1)).save(existingPerformance);
    }

    @Test
    public void testDeletePerformance() {
        Long id = 1L;
        performanceService.deletePerformance(id);
        verify(performanceRepository, times(1)).deleteById(id);
    }

    @Test
    public void testGetPerformancesByParticipant() {
        List<Performance> performances = Arrays.asList(performance);
        when(performanceRepository.findAllByParticipant(performance.getParticipant())).thenReturn(performances);
        List<Performance> result = performanceService.getPerformancesByParticipant(performance.getParticipant());
        assertEquals(performances, result);
        verify(performanceRepository, times(1)).findAllByParticipant(performance.getParticipant());
    }
}
