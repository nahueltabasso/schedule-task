package scheduletask.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scheduletask.app.models.entity.Task;
import scheduletask.app.models.repository.TaskRepository;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        List<Task> tasks = (List<Task>) taskRepository.findAll();
        return tasks;
    }
}
