package ds.service;


import ds.domain.Internship;
import ds.repository.InternshipRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class InternshipServiceTest {

    @Mock
    private InternshipRepository internshipRepository;

    @InjectMocks
    private InternshipServiceImpl internshipService;

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
    public void testGetAllInternships() {
        List<Internship> internships = Arrays.asList(internship);
        when(internshipRepository.findAll()).thenReturn(internships);
        List<Internship> result = internshipService.getAllInternships();
        assertEquals(internships, result);
    }

    @Test
    public void testGetInternshipById() {
        when(internshipRepository.getById(1L)).thenReturn(internship);
        Internship result = internshipService.getInternshipById(1L);
        assertEquals(internship, result);
    }

    @Test
    public void testCreateInternship() {
        when(internshipRepository.save(internship)).thenReturn(internship);
        Internship result = internshipService.createInternship(internship);
        assertEquals(internship, result);
    }

    @Test
    public void testUpdateInternship() {
        Internship existingInternship = new Internship();
        existingInternship.setId(1L);
        existingInternship.setName("Internship 1");
        Internship updatedInternship = new Internship();
        updatedInternship.setId(1L);
        updatedInternship.setName("Updated Internship 1");
        when(internshipRepository.getById(1L)).thenReturn(existingInternship);
        when(internshipRepository.save(existingInternship)).thenReturn(updatedInternship);
        Internship result = internshipService.updateInternship(1L, updatedInternship);
        assertEquals(updatedInternship, result);
    }

    @Test
    public void testGetInternshipByStatus() {
        when(internshipRepository.findAllByStatus("ACTIVE")).thenReturn(Arrays.asList(internship));
        List<Internship> result = internshipService.getInternshipsByStatus("ACTIVE");
        assertEquals(Arrays.asList(internship), result);
    }

}
