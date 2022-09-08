package scheduletask.app.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "eventdate")
    private LocalDateTime eventDate;
    @Column(name = "eventtime")
    private LocalTime eventTime;
    @Column(name = "username")
    private String username;
    @Column(name = "revised")
    private Boolean revised;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "creationtimestamp")
    private LocalDateTime creationTimeStamp;
    @Column(name = "creationuser")
    private String creationUser;
    @Column(name = "modificationtimestamp")
    private LocalDateTime modificationTimeStamp;
    @Column(name = "modificationuser")
    private String modificationUser;

}
