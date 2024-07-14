package com.example.training.elearning.service;

import com.example.training.elearning.model.Author;
import com.example.training.elearning.repository.EAuthorRepository;
import com.example.training.elearning.specification.AuthorSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
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

    public void findByAgeAndPartOfFirstName(int age, String partOfFirstName) {
        Specification<Author> spec = Specification
                .where(AuthorSpecification.hasAge(age))
                .or(AuthorSpecification.firstnameLike(partOfFirstName));
        eAuthorRepository.findAll(spec).forEach(System.out::println);
    }
}