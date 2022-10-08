package scheduletask.app.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import scheduletask.app.models.entity.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query(value = "select * " +
                "from tasks " +
                "where active = ?2 " +
                "and revised = ?1 " +
                "and eventdate = ?3 " +
                "and username = ?4 ", nativeQuery = true)
    public List<Task> findByRevisedIsFalseAndByActiveIsTrueAndByEventDateAndByUsername(Boolean revised, Boolean active, LocalDate eventDate, String username);

    List<Task> findByUsernameLikeAndActiveTrueAndRevisedFalseOrderByEventDateAsc(String username);

    List<Task> findByEventDateBefore(LocalDate currentDate);


}
