package scheduletask.app.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
    @NotNull
    @NotEmpty
    private String description;
    @Column(name = "eventdate")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;
    @Column(name = "eventtime")
    @NotNull
    private LocalTime eventTime;
    @Column(name = "username")
    @Email
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
