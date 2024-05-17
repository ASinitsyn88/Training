package com.example.training.batch.fileupload.config;

import com.example.training.batch.fileupload.student.Student;
import com.example.training.batch.fileupload.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    private static final String JOB_NAME = "importStudents";
    // Number of file lines, the processing of which is indivisible operation and is committed
    private static final int CHUNK_SIZE = 10;
    private static final int NUMBER_OF_THREADS = 10;
    private static final String DELIMITER = ",";
    private static final String[] COLUMNS = {"firstname", "lastname", "age"};

    private final JobRepository jobRepository;
    private final StudentRepository repository;
    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(NUMBER_OF_THREADS);
        return asyncTaskExecutor;
    }

    @Bean
    public FlatFileItemReader<Student> itemReader() {
        FlatFileItemReader<Student> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new ClassPathResource("/batch/csv/students.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1); // Do not read header
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    @Bean
    public StudentProcessor processor() {
        return new StudentProcessor();
    }

    @Bean
    public RepositoryItemWriter<Student> writer() {
        RepositoryItemWriter<Student> writer = new RepositoryItemWriter<>();
        writer.setRepository(repository);
        writer.setMethodName("save"); // Execute "save" method of our repository (StudentRepository)
        return writer;
    }

    @Bean
    public Step importStep() {
        return new StepBuilder("csvImport", jobRepository)
                .<Student, Student>chunk(CHUNK_SIZE, platformTransactionManager)
                .reader(itemReader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job runImportStudentsJob() {
        return new JobBuilder(JOB_NAME, jobRepository)
                .start(importStep()) // We have only one step
                .build();
    }

    private LineMapper<Student> lineMapper() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(DELIMITER);
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(COLUMNS);

        BeanWrapperFieldSetMapper<Student> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Student.class);

        DefaultLineMapper<Student> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
}