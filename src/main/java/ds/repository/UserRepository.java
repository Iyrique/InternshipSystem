package ds.repository;

import ds.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User getUserById(Long id);

    User getUserByUsername(String username);

    List<User> findAllByRole(String role);

    void deleteById(Long id);

}
