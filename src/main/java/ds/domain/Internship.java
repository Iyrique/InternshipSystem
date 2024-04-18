package ds.domain;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "internships")
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate endDateRecording;
    private Statuses status;

    private enum Statuses {
        STARTED ("Started"),
        ACTIVE ("Active"),
        ENDED ("Ended");

        Statuses(String value) {
        }
    }
}
