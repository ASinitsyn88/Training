package com.example.training.elearning.service;

import com.example.training.elearning.model.Author;
import com.example.training.elearning.repository.EAuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EAuthorService {
    private final EAuthorRepository eAuthorRepository;

    public List<Author> findByAge(int age) {
        return eAuthorRepository.findByNamedQuery(age);
    }
}