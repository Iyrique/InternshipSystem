package ds.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "performances")
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    private String status;
    private String comment;
}
