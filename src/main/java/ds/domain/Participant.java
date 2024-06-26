package ds.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "participants")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id")
    private Long id;

    private String fullName;
    private String email;
    private String phoneNumber;
    private String username;
    private String telegramId;
    private String about;
    private LocalDate dateOfBirth;
    private String city;
    private String educationStatus;
    private String university;
    private String faculty;
    private String specialty;
    private int course;
}
