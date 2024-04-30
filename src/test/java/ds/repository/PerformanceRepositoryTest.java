package ds.repository;


import ds.domain.Internship;
import ds.domain.Participant;
import ds.domain.Performance;
import ds.domain.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PerformanceRepositoryTest {

    @Mock
    private PerformanceRepository performanceRepository;

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
    public void testGetById() {
        when(performanceRepository.getById(1L)).thenReturn(performance);
        Performance result = performanceRepository.getById(1L);
        assertEquals(result, performance);
    }

    @Test
    public void testFindAllByParticipant() {
        List<Performance> performanceList = Arrays.asList(performance);
        when(performanceRepository.findAllByParticipant(performance.getParticipant())).thenReturn(performanceList);
        List<Performance> result = performanceRepository.findAllByParticipant(performance.getParticipant());
        assertEquals(performanceList, result);
    }
}
