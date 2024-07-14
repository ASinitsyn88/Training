package com.example.training.elearning.specification;

import com.example.training.elearning.model.Author;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecification {

    // Specification aka filter by age
    public static Specification<Author> hasAge(int age) {
        return (Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (age < 0) {
                return null;
            }
            return builder.equal(root.get("age"), age);
        };
    }

    // Specification aka filter by firstname
    public static Specification<Author> firstnameLike(String firstname) {
        return (Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (firstname == null) {
                return null;
            }
            return builder.like(root.get("firstName"), "%" + firstname + "%");
        };
    }
}