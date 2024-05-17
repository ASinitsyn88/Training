package com.example.training.batch.fileupload.config;

import com.example.training.batch.fileupload.student.Student;
import org.springframework.batch.item.ItemProcessor;

/**
 * ItemProcessor<Student, Student>
 * 1 - input object (Student)
 * 2 - output object (Student)
 */
public class StudentProcessor implements ItemProcessor<Student, Student> {

    // All the business logic goes here
    @Override
    public Student process(Student student) throws Exception {
        return student;
    }
}