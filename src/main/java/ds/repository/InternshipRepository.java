package ds.repository;

import ds.domain.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipRepository extends JpaRepository<Internship, Long> {

    List<Internship> findByStatus(String status);

    List<Internship> findAll();

}
