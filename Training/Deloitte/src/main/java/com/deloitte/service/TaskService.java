package com.deloitte.service;

import com.deloitte.entity.TaskEntity;
import com.deloitte.entity.UserEntity;
import com.deloitte.model.TaskModel;
import com.deloitte.repository.TaskRepository;
import com.deloitte.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static com.deloitte.model.TaskModel.from;
import static com.deloitte.model.TaskModel.to;

@Service
public class TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public TaskModel findById(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task with id " + id + " doesn't exists"));
        return from(taskEntity);
    }

    @Transactional(readOnly = true)
    public List<TaskModel> findByUserLogin(String login) {
        UserEntity userEntity = findUserByLogin(login);
        return from(userEntity.getTasks());
    }

    @Transactional(readOnly = false)
    public UserEntity saveOrUpdateTask(String login, TaskModel task) {
        UserEntity userEntity = findUserByLogin(login);
        if (task.getId() == null) {
            userEntity.getTasks().add(to(task));
        } else {
            userEntity.getTasks().removeIf(t -> t.getId().equals(task.getId()));
            userEntity.getTasks().add(to(task));
        }
        return userRepository.save(userEntity);
    }

    @Transactional(readOnly = false)
    public void deleteByLoginAndTaskId(String login, Long taskId) {
        UserEntity userEntity = findUserByLogin(login);
        userEntity.getTasks().removeIf(t -> t.getId().equals(taskId));
        userRepository.save(userEntity);
    }

    private UserEntity findUserByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User with login " + login + " doesn't exists"));
    }
}