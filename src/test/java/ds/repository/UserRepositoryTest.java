package ds.repository;


import ds.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    private static User user;

    @BeforeAll
    public static void prepareTestData() {
        Participant participant = Participant.builder()
                .id(1L)
                .about("test")
                .city("test")
                .course(1)
                .dateOfBirth(LocalDate.of(2005, 1, 1))
                .educationStatus("test")
                .email("test")
                .faculty("test")
                .fullName("test")
                .phoneNumber("test")
                .telegramId("test")
                .university("test")
                .username("test")
                .build();

        Task task = Task.builder()
                .id(1L)
                .gitlabRepository("test")
                .name("test")
                .internship(Internship.builder()
                        .id(1L)
                        .description("Test")
                        .endDate(LocalDate.of(2024, 5, 10))
                        .endDateRecording(LocalDate.of(2024, 4, 26))
                        .name("IntTest")
                        .startDate(LocalDate.of(2024, 4, 27))
                        .status("ACTIVE")
                        .build())
                .build();

        Message message1 = Message.builder()
                .id(1L)
                .message("test")
                .participant(participant)
                .task(task)
                .read(true)
                .build();

        Message message2 = Message.builder()
                .id(2L)
                .message("test")
                .participant(participant)
                .task(task)
                .read(true)
                .build();
        user = User.builder()
                .id(1L)
                .username("voronezhskiy.nikita")
                .password("password")
                .role("ADMIN")
                .message(Arrays.asList(message1, message2))
                .build();
    }

    @Test
    public void testFindAll() {
        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);
        List<User> result = userRepository.findAll();
        assertEquals(result, users);
    }

    @Test
    public void testGetUserById() {
        when(userRepository.getUserById(user.getId())).thenReturn(user);
        User exUser = userRepository.getUserById(user.getId());
        assertEquals(user, exUser);
    }

    @Test
    public void testGetUserByUsername() {
        when(userRepository.getUserByUsername("voronezhskiy.nikita")).thenReturn(user);
        User exUser = userRepository.getUserByUsername("voronezhskiy.nikita");
        assertEquals(user, exUser);
    }

    @Test
    public void testFindAllByStatus() {
        String role = "ROLE_USER";
        User user1 = new User(1L, "user1", "password", new ArrayList<>(),role);
        User user2 = new User(2L, "user2", "password", new ArrayList<>(),role);
        List<User> userList = Arrays.asList(user1, user2);
        when(userRepository.findAllByRole(role)).thenReturn(userList);
        List<User> result = userRepository.findAllByRole(role);
        assertEquals(2, result.size());
        assertEquals(userList, result);
    }

    @Test
    public void testDeleteUserById() {
        Long userId = 1L;
        userRepository.deleteById(userId);
        verify(userRepository).deleteById(userId);
    }

}
