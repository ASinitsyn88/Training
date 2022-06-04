package com.deloitte.service;

import com.deloitte.AbstractTest;
import com.deloitte.entity.TaskEntity;
import com.deloitte.entity.UserEntity;
import com.deloitte.model.TaskModel;
import com.deloitte.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import static com.deloitte.entity.Role.ADMIN;
import static com.deloitte.entity.Status.ACTIVE;
import static com.deloitte.model.TaskModel.formatter;
import static java.time.LocalDateTime.now;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest extends AbstractTest {

    private final TaskService taskService;
    private final UserRepository userRepository;

    @Autowired
    public TaskServiceTest(TaskService taskService, UserRepository userRepository) {
        this.taskService = taskService;
        this.userRepository = userRepository;
    }

    @Transactional
    @Test
    public void findById_userWithTasksTest() {
        // Arrange
        UserEntity user = userRepository.findByLogin("test").orElseThrow(() -> new IllegalArgumentException("Standart user test doesn't exist"));
        user.getTasks().add(new TaskEntity(null, "ten", now(), false));
        userRepository.save(user);
        UserEntity userWithTask = userRepository.findByLogin("test").orElseThrow(() -> new IllegalArgumentException("Standart user test doesn't exist"));
        Optional<TaskEntity> taskEntity = userWithTask.getTasks().stream().findFirst();

        // Act
        TaskModel sut = null;
        if (taskEntity.isPresent()) {
           sut = taskService.findById(taskEntity.get().getId());
        } else {
            fail();
        }

        // Assert
        assertNotNull(sut.getId());
        assertEquals("ten", sut.getDescription());
        assertNotNull(sut.getModifytimestamp());
    }

    @Test
    public void findById_idNotExistTest() {
        // Act
        assertThrows(IllegalArgumentException.class, () -> taskService.findById(-1001L));
    }

    @Test
    public void findByUserLogin_userWithoutTasksTest() {
        // Arrange
        UserEntity user = createUser("user_without_tasks", emptyList());
        userRepository.save(user);

        // Act
        List<TaskModel> sut = taskService.findByUserLogin("user_without_tasks");

        // Assert
        assertNotNull(sut);
        assertTrue(sut.isEmpty());
    }

    @Test
    public void findByUserLogin_userWithTasksTest() {
        // Arrange
        UserEntity user = createUser("user_with_tasks", singletonList("one"));
        userRepository.save(user);

        // Act
        List<TaskModel> sut = taskService.findByUserLogin("user_with_tasks");

        // Assert
        assertNotNull(sut);
        assertEquals(1, sut.size());
        assertNotNull(sut.get(0).getId());
        assertEquals("one", sut.get(0).getDescription());
        assertNotNull(sut.get(0).getModifytimestamp());
    }

    @Test
    public void findByUserLogin_loginNotExistTest() {
        // Act
        assertThrows(UsernameNotFoundException.class, () -> taskService.findByUserLogin("not_exist"));
    }

    @Transactional
    @Test
    public void saveOrUpdateTaskTest_saveTest() {
        // Act
        UserEntity user = taskService.saveOrUpdateTask("test", new TaskModel(null, "new task 1", now().format(formatter), false));

        // Assert
        assertNotNull(user);
        assertNotNull(user.getTasks());
        assertFalse(user.getTasks().isEmpty());
        Optional<TaskEntity> sut = user.getTasks().stream().filter(t -> t.getDescription().equals("new task 1")).findFirst();
        assertTrue(sut.isPresent());
        assertNotNull(sut.get().getId());
    }

    @Transactional
    @Test
    public void saveOrUpdateTaskTest_updateTest() {
        // Arrange
        UserEntity user = taskService.saveOrUpdateTask("test", new TaskModel(null, "new task 2", now().format(formatter), false));

        assertNotNull(user);
        assertNotNull(user.getTasks());
        assertFalse(user.getTasks().isEmpty());
        Optional<TaskEntity> savedTask = user.getTasks().stream().filter(t -> t.getDescription().equals("new task 2")).findFirst();
        assertTrue(savedTask.isPresent());
        assertNotNull(savedTask.get().getId());

        TaskModel forUpdate = TaskModel.from(savedTask.get());
        forUpdate.setDescription("new task 22");

        // Act
        UserEntity user1 = taskService.saveOrUpdateTask("test", forUpdate);

        // Assert
        assertNotNull(user);
        assertNotNull(user.getTasks());
        assertFalse(user.getTasks().isEmpty());
        Optional<TaskEntity> sut = user.getTasks().stream().filter(t -> t.getDescription().equals("new task 22")).findFirst();
        assertTrue(sut.isPresent());
        assertNotNull(sut.get().getId());
    }

    @Test
    public void saveOrUpdateTaskTest_loginNotExistTest() {
        assertThrows(UsernameNotFoundException.class,
                () -> taskService.saveOrUpdateTask("not_exist", new TaskModel(null, "new task 2", now().format(formatter), false)));
    }

    @Transactional
    @Test
    public void deleteByLoginAndTaskId_successTest() {
        // Arrange
        UserEntity user = taskService.saveOrUpdateTask("test", new TaskModel(null, "new task 3", now().format(formatter), false));
        TaskEntity savedTask = user.getTasks().stream().filter(t -> t.getDescription().equals("new task 3")).findFirst().orElseThrow(IllegalArgumentException::new);

        // Act
        taskService.deleteByLoginAndTaskId("test", savedTask.getId());

        // Assert
        List<TaskModel> sut = taskService.findByUserLogin("test");
        Optional<TaskModel> deletedTask = sut.stream().filter(t -> t.getDescription().equals("new task 3")).findFirst();
        assertFalse(deletedTask.isPresent());
    }

    @Test
    public void deleteByLoginAndTaskId_loginNotExistTest() {
        assertThrows(UsernameNotFoundException.class,
                () -> taskService.deleteByLoginAndTaskId("not_exist", 0L));
    }

    private UserEntity createUser(String login, List<String> taskDescriptions) {
        UserEntity user = new UserEntity();
        user.setLogin(login);
        user.setFirstName("UserName");
        user.setLastName("UserLastName");
        user.setPassword("$2a$12$Cc4J/uZOmY/tdob8ivc.ZOdWjcE6cbuzHrUSEwnBwh90pVgf/Y7TG");
        user.setRole(ADMIN);
        user.setStatus(ACTIVE);
        if (taskDescriptions != null && !taskDescriptions.isEmpty()) {
            taskDescriptions.forEach(d -> user.getTasks().add(createTask(d)));
        }
        return user;
    }

    private TaskEntity createTask(String description) {
        TaskEntity task = new TaskEntity();
        task.setDescription(description);
        task.setModifytimestamp(now());
        return task;
    }
}