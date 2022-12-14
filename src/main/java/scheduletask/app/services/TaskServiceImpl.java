package scheduletask.app.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import scheduletask.app.models.entity.Task;
import scheduletask.app.models.repository.TaskRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Autowired
    private EmailService emailService;
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
        log.info("Execute: {}", funcName);

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
                                // Notify to user by email
                                emailService.sendMail(task);
                                // Update and persist the object
                                task.setRevised(true);
                                task.setActive(false);
                                taskRepository.save(task);
                                log.info("Task updating successfully");
                            }
                        }

                    }
                }

            } catch (Exception e) {
                log.error("ERROR: an error occurred");
                log.error("ERROR: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Task> getTasksByUsername(String username) {
        String funcName = this.getClass().getName() + ".getTasksByUsername()";
        log.info("Execute: {}", funcName);
        List<Task> taskList = new ArrayList<>();
        taskList = taskRepository.findByUsernameLikeAndActiveTrueAndRevisedFalseOrderByEventDateAsc(username);
        return taskList;
    }

    @Override
    public void cancelTask(Long id) {
        String funcName = this.getClass().getName() + ".cancelTask()";
        log.info("Execute: {}", funcName);
        try {
            Task task = taskRepository.findById(id).get();
            if (task == null) {
                throw new Exception("Not exist task by id " + id);
            }
            task.setActive(false);
            taskRepository.save(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(Long id) {
        String funcName = this.getClass().getName() + ".deleteTask()";
        log.info("Execute: {}", funcName);
        try {
            Task task = taskRepository.findById(id).get();
            if (task == null) {
                throw new Exception("Not exist task by id " + id);
            }
            taskRepository.delete(task);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Task save(Task task) throws Exception {
        String funcName = this.getClass().getName() + ".save()";
        log.info("Execute: {}", funcName);

        // Set the audit properties
        task.setCreationUser(task.getUsername());
        task.setCreationTimeStamp(LocalDateTime.now());
        task.setModificationUser(task.getUsername());
        task.setModificationTimeStamp(LocalDateTime.now());

        // Set the boolean values
        task.setActive(Boolean.TRUE);
        task.setRevised(Boolean.FALSE);

        // Valid the event date greater or equal than current date
        LocalDate currentDate = LocalDate.now();
        LocalDate eventDate = LocalDate.from(task.getEventDate());
        if (eventDate.isBefore(currentDate)) {
            throw new Exception("Event Date not valid. Event Date can not be before current date!");
        }

        // Valid the event time
        LocalTime currentTime = LocalTime.now();
        LocalTime eventTime = task.getEventTime();
        Long difference = ChronoUnit.MINUTES.between(currentTime, eventTime);
        if (HALF_HOUR >= difference && eventDate.equals(currentDate)) {
            throw new Exception("Event Time not valid. Event time can be after 30 minutes from now");
        }

        task = taskRepository.save(task);

        return task;
    }

    @Override
    public void deleteOldTasks() {
        String funcName = this.getClass().getName() + ".deleteOldTasks()";
        log.info("Execute: {}", funcName);
        List<Task> tasksList = taskRepository.findByEventDateBefore(LocalDate.now());
        taskRepository.deleteAll(tasksList);
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
