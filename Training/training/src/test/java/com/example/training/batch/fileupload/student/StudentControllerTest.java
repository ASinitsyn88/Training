package com.example.training.batch.fileupload.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // To be able to use @BeforeAll and @AfterAll in non-static methods
public class StudentControllerTest {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentController studentController;

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    public void importCsvToDBJob_Test() {
        // Given | When
        studentController.importCsvToDBJob();
        // Then
        List<Student> students = studentRepository.findAll();

        assertNotNull(students);
        assertEquals(30, students.size());
    }
}