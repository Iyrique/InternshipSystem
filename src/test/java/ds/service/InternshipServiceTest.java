package ds.service;

import ds.domain.Internship;
import ds.repository.InternshipRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class InternshipServiceTest {

    @Mock
    private InternshipRepository internshipRepository;

    @InjectMocks
    private InternshipService internshipService;

    private static Internship internship1;

    @BeforeAll
    public static void prepareTestData() {
        internship1 = Internship.builder()
                .id(1L)
                .name("IntTest")
                .description("Test")
                .startDate(LocalDate.of(2024, 4, 27))
                .endDate(LocalDate.of(2024, 5, 10))
                .endDateRecording(LocalDate.of(2024, 4, 26))
                .status("ACTIVE")
                .build();
    }

    @Test
    public void testGetInternshipById() {
        when(internshipRepository.findById(internship1.getId())).thenReturn(Optional.of(internship1));
        Internship result = internshipService.getInternshipById(internship1.getId());
        assertNotNull(result);
        assertSame(internship1.getId(), result.getId());
        assertEquals(internship1.getName(), result.getName());
    }
}
