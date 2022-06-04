package com.deloitte.controller;

import com.deloitte.model.TaskModel;
import com.deloitte.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    @PreAuthorize("hasAuthority('read')")
    public String getAll(HttpServletRequest request, Model model) {
        List<TaskModel> tasks = taskService.findByUserLogin(request.getUserPrincipal().getName());
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    @GetMapping("/task-create")
    @PreAuthorize("hasAuthority('write')")
    public String createTaskForm(TaskModel task) {
        return "task-create";
    }

    @PostMapping("/task-create")
    @PreAuthorize("hasAuthority('write')")
    public String createTask(HttpServletRequest request, TaskModel task) {
        taskService.saveOrUpdateTask(request.getUserPrincipal().getName(), task);
        return "redirect:/api/v1/tasks";
    }

    @GetMapping("/task-delete/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String deleteTask(@PathVariable("id") Long id, HttpServletRequest request) {
        taskService.deleteByLoginAndTaskId(request.getUserPrincipal().getName(), id);
        return "redirect:/api/v1/tasks";
    }

    @GetMapping("/task-update/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String updateTaskForm(@PathVariable("id") Long id, Model model) {
        TaskModel task = taskService.findById(id);
        model.addAttribute("task", task);
        return "task-update";
    }

    @PostMapping("/task-update")
    @PreAuthorize("hasAuthority('write')")
    public String updateTask(HttpServletRequest request, TaskModel task) {
        taskService.saveOrUpdateTask(request.getUserPrincipal().getName(), task);
        return "redirect:/api/v1/tasks";
    }
}