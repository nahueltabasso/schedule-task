package scheduletask.app.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import scheduletask.app.models.entity.Task;
import scheduletask.app.models.repository.TaskRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    private static final Long HALF_HOUR = 30L;    // 30 minutes equals to half hour
    @Autowired
    private TaskRepository taskRepository;
    @Value(value = "${tasks.emails}")
    private String emails;

    @Override
    public List<Task> findAll() {
        List<Task> tasks = (List<Task>) taskRepository.findAll();
        return tasks;
    }

    @Override
    public void notifyTasksByEmail() {
        String funcName = this.getClass().getName() + ".notifyTasksByEmail()";
        log.info("Execute: {}", funcName );

        List<String> emailsList = List.of(emails.split(";"));

        if (emailsList.size() > 0) {
            try {
                log.info("Iterate the list of emails");
                for (String email : emailsList) {
                    log.info("Retrieve the tasks by current email");
                    log.info("Email: {}", email);
                    List<Task> taskList = new ArrayList<>();
                    taskList = taskRepository.findByRevisedIsFalseAndByActiveIsTrueAndByEventDateAndByUsername(false,
                            true, LocalDate.now(), email);

                    for (Task task: taskList) {

                        if (validEventDate(task)) {
                            if (validEventTime(task)) {
                                // Notify to user by eail


                                // Update and persist the object
                                task.setRevised(true);
                                task.setActive(false);
                                taskRepository.save(task);
                            }
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        System.out.println(emails);
    }

    private boolean validEventDate(Task task) {
        String funcName = this.getClass().getName() + ".validEventDate()";
        log.info("Execute: {}", funcName );

        LocalDate currentDate = LocalDate.now();
        LocalDate eventDate = LocalDate.from(task.getEventDate());

        if (currentDate.equals(eventDate)) {
            return true;
        }
        return false;
    }

    private boolean validEventTime(Task task) {
        String funcName = this.getClass().getName() + ".validEventTime()";
        log.info("Execute: {}", funcName );

        LocalTime currentTime = LocalTime.now();
        LocalTime eventTime = task.getEventTime();

        long difference = ChronoUnit.MINUTES.between(currentTime, eventTime);

        if (difference <= HALF_HOUR) {
            return true;
        }
        return false;
    }
}
