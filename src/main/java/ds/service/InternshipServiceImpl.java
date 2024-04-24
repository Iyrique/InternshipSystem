package ds.service;

import ds.domain.Internship;
import ds.repository.InternshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipServiceImpl implements InternshipService{

    private final InternshipRepository internshipRepository;

    @Override
    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    @Override
    public Internship getInternshipById(Long id) {
        return internshipRepository.getById(id);
    }

    @Override
    @Transactional
    public Internship createInternship(Internship internship) {
        return internshipRepository.save(internship);
    }

    @Override
    @Transactional
    public Internship updateInternship(Long id, Internship internship) {
        Internship existingInternship = getInternshipById(id);
        if (existingInternship != null) {
            existingInternship.setName(internship.getName());
            existingInternship.setDescription(internship.getDescription());
            existingInternship.setStartDate(internship.getStartDate());
            existingInternship.setEndDate(internship.getEndDate());
            existingInternship.setStatus(internship.getStatus());
            existingInternship.setEndDateRecording(internship.getEndDateRecording());
            return internshipRepository.save(existingInternship);
        }
        return null;
    }

    @Override
    public List<Internship> getInternshipsByStatus(String status) {
        return internshipRepository.getAllByStatus(status);
    }
}
