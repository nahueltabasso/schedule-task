package scheduletask.app.models.repository;

import org.springframework.data.repository.CrudRepository;
import scheduletask.app.models.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
