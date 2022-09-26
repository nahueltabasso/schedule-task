package scheduletask.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import scheduletask.app.models.entity.Task;
import scheduletask.app.services.TaskService;

import java.util.List;

@Slf4j
@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;
    private String username;

    @RequestMapping("/login")
    public String showUserInput(Model model) {
        String funcName = this.getClass().getName() + ".showUserInput()";
        log.info("Execute: {}", funcName);
        model.addAttribute("mainTitle", "Task App");
        model.addAttribute("title", "Enter your username");
        model.addAttribute("username", username);
        return "login";
    }

    @RequestMapping(value = {"/list-tasks"})
    public String showTaskList(Model model, @RequestParam String username) {
        String funcName = this.getClass().getName() + ".showTaskList()";
        log.info("Execute: {}", funcName);
        this.username = username;
        List<Task> taskList = taskService.getTasksByUsername(this.username);
        model.addAttribute("title", "Tasks");
        model.addAttribute("taskList", taskList);
        return "list-task";
    }

    @RequestMapping(value = {"/cancel-task/{id}"}, method = RequestMethod.GET)
    public String cancelTask(@PathVariable(value = "id") Long id, Model model) {
        String funcName = this.getClass().getName() + ".cancelTask()";
        log.info("Execute: {}", funcName);

        if (id != null) {
            taskService.cancelTask(id);
        }
        return "redirect:/list-tasks?username=" + this.username;
    }

    @RequestMapping(value = {"/delete-task/{id}"}, method = RequestMethod.GET)
    public String deleteTask(@PathVariable(value = "id") Long id, Model model) {
        String funcName = this.getClass().getName() + ".deleteTask()";
        log.info("Execute: {}", funcName);

        if (id != null) {
            taskService.deleteTask(id);
        }
        return "redirect:/list-tasks?username=" + this.username;
    }

}
