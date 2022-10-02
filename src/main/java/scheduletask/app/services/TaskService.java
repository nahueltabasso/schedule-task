package scheduletask.app.services;

import scheduletask.app.models.entity.Task;

import java.util.List;

public interface TaskService {

    public List<Task> findAll();
    public void notifyTasksByEmail();
    public List<Task> getTasksByUsername(String username);
    public void cancelTask(Long id);
    public void deleteTask(Long id);
    public Task save(Task task) throws Exception;
}
