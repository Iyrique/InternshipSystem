package ds.service;

import ds.domain.User;
import ds.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetAllUsers() {
        List<User> expectedUsers = Collections.singletonList(new User());
        when(userRepository.findAll()).thenReturn(expectedUsers);
        List<User> actualUsers = userService.getAllUsers();
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        User createdUser = userService.createUser(user);
        assertEquals(user, createdUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User expectedUser = new User();
        when(userRepository.getUserById(userId)).thenReturn(expectedUser);
        User actualUser = userService.getUserById(userId);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testGetUserByUsername() {
        String username = "test";
        User expectedUser = new User();
        when(userRepository.getUserByUsername(username)).thenReturn(expectedUser);
        User actualUser = userService.getUserByUsername(username);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testGetUsersByRole() {
        String role = "admin";
        List<User> expectedUsers = Collections.singletonList(new User());
        when(userRepository.findAllByRole(role)).thenReturn(expectedUsers);
        List<User> actualUsers = userService.getUsersByRole(role);
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        User existingUser = new User();
        User updatedUser = new User();
        updatedUser.setRole("admin");
        when(userRepository.getUserById(userId)).thenReturn(existingUser);
        when(userRepository.save(existingUser)).thenReturn(updatedUser);
        User result = userService.updateUser(userId, updatedUser);
        assertEquals(updatedUser, result);
        assertEquals("admin", result.getRole());
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;
        userService.deleteUser(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }
}
