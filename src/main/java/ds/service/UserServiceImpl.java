package ds.service;

import ds.domain.Message;
import ds.domain.User;
import ds.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    @Transactional
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.getUserById(id);
        if (existingUser != null) {
            existingUser.setRole(user.getRole());
            existingUser.setMessage(user.getMessage());
            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void createAndSendAdminMessage(String text) {
        List<User> users = getUsersByRole("ADMIN");
        for (User user : users) {
            updateUser(user.getId(), addedMessage(user, text));
        }
    }

    @Transactional
    public void sendMessageByUsername(String username, String text) {
        User user = getUserByUsername(username);
        updateUser(user.getId(), addedMessage(user, text));
    }

    private User addedMessage(User user, String text) {
        Message message = new Message();
        message.setMessage(text);
        List<Message> messages = user.getMessage();
        messages.add(message);
        user.setMessage(messages);
        return user;
    }
}
