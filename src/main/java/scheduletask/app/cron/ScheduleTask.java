package scheduletask.app.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import scheduletask.app.models.entity.Task;
import scheduletask.app.services.TaskService;

import java.util.Date;
import java.util.List;

@Component
public class ScheduleTask {

    @Autowired
    private TaskService taskService;

    @Scheduled(cron = "*/10 * * * * *")
    public void sheduledTask() {
        List<Task> tasks = taskService.findAll();
        System.out.println(new Date());
    }

}
