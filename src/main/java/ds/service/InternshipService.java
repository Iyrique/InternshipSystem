package ds.service;

import ds.domain.Internship;

import java.util.List;

public interface InternshipService {

    List<Internship> getAllInternships();

    Internship getInternshipById(Long id);

    Internship createInternship(Internship internship);

    Internship updateInternship(Long id, Internship internship);

    List<Internship> getInternshipsByStatus(String status);
}
