package ds.service;

import ds.domain.Message;
import ds.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User createUser(User user);

    User getUserByUsername(String username);

    List<User> getUsersByRole(String role);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

}
