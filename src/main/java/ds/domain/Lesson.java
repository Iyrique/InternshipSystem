package ds.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long id;

    private String name;
    private String description;
    private LocalDateTime dateTime;
    private String status;

    @ManyToOne
    @JoinColumn(name = "internship_id")
    private Internship internship;
}
