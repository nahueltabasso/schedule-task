package scheduletask.app.services;

import scheduletask.app.models.entity.Task;

import java.util.List;

public interface TaskService {

    public List<Task> findAll();
}
