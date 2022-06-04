package com.deloitte.model;

import com.deloitte.entity.TaskEntity;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static com.deloitte.model.TaskModel.formatter;
import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.*;

public class TaskModelTest {

    @Test
    public void from_Test() {
        // Act
        TaskModel sut = TaskModel.from(new TaskEntity(1L, "one", now(), false));

        // Assert
        assertNotNull(sut);
        assertEquals(Long.valueOf(1L), sut.getId());
        assertEquals("one", sut.getDescription());
        assertNotNull(sut.getModifytimestamp());
    }

    @Test
    public void from_setTest() {
        // Arrange
        Set<TaskEntity> taskEntitySet = new HashSet<>();
        taskEntitySet.add(new TaskEntity(1L, "one", now(), false));

        // Act
        List<TaskModel> sut = TaskModel.from(taskEntitySet);

        // Assert
        assertNotNull(sut);
        assertEquals(Long.valueOf(1L), sut.get(0).getId());
        assertEquals("one", sut.get(0).getDescription());
        assertNotNull(sut.get(0).getModifytimestamp());
    }

    @Test
    public void to_Test() {
        // Arrange
        TaskModel taskModel = new TaskModel();
        taskModel.setId(1L);
        taskModel.setDescription("one");
        taskModel.setModifytimestamp(now().format(formatter));

        // Act
        TaskEntity sut = TaskModel.to(taskModel);

        // Assert
        assertNotNull(sut);
        assertNull(sut.getId());
        assertEquals("one", sut.getDescription());
        assertNotNull(sut.getModifytimestamp());
    }
}