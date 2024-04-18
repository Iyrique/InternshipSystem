package ds.domain;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gitlabRepository;

    @ManyToOne
    @JoinColumn(name = "internship_id")
    private Internship internship;
}
