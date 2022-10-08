package scheduletask.app.cron;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import scheduletask.app.services.TaskService;

import java.time.LocalDateTime;

@Slf4j
@Component
public class ScheduleTask {

    @Autowired
    private TaskService taskService;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void sheduledTask() {
        log.info("Execute sheduledTask cron process start at " + LocalDateTime.now());
        taskService.notifyTasksByEmail();
        log.info("Execute sheduledTask cron process end at " + LocalDateTime.now());
    }

    @Scheduled(cron = "0 3 * * * ?")
    public void debugDb() {
        log.info("Execute debuDb cron process start at " + LocalDateTime.now());
        taskService.deleteOldTasks();
        log.info("Execute debuDb cron process end at " + LocalDateTime.now());
    }


}
