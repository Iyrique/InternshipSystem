package ds.repository;

import ds.domain.Internship;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class InternshipRepositoryTest {
    @Mock
    private InternshipRepository internshipRepository;

    private static Internship internship;

    @BeforeAll
    public static void prepareTestData() {
        internship = Internship.builder()
                .id(1L)
                .description("Test")
                .endDate(LocalDate.of(2024, 5, 10))
                .endDateRecording(LocalDate.of(2024, 4, 26))
                .name("IntTest")
                .startDate(LocalDate.of(2024, 4, 27))
                .status("ACTIVE")
                .build();
    }

    @Test
    public void getInternshipTest() {
        when(internshipRepository.findById(1L)).thenReturn(Optional.of(internship));
        Optional<Internship> result = internshipRepository.findById(1L);
        assertEquals(Optional.of(internship), result);
    }

    @Test
    public void saveInternshipTest() {
        doAnswer(new Answer<Internship>() {
            @Override
            public Internship answer(InvocationOnMock invocation) throws Throwable {
                Internship savedInternship = invocation.getArgument(0);
                if (savedInternship.getId() == null) {
                    savedInternship.setId(1L);
                }
                return savedInternship;
            }
        }).when(internshipRepository).save(any(Internship.class));
        Internship savedInternship = internshipRepository.save(internship);
        assertNotNull(savedInternship);
        assertEquals(internship.getId(), savedInternship.getId());
    }

    @Test
    public void testGetAllByStatus() {
        List<Internship> internshipsWithActiveStatus = Arrays.asList(internship);
        when(internshipRepository.findAllByStatus("ACTIVE")).thenReturn(internshipsWithActiveStatus);
        List<Internship> result = internshipRepository.findAllByStatus("ACTIVE");
        assertEquals(1, result.size());
        assertEquals(internship, result.get(0));
    }
}
