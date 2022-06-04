package com.example.springsecuritydemo.rest;

import com.example.springsecuritydemo.model.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

/**
 * Класс нужен для проверки работы авторизаций. Все методы являются заглушками и не обращаются за реальными данными в БД
 */
@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestControllerV1 {
    private final List<Developer> DEVELOPERS = Stream.of(
            new Developer(1L, "Ivan", "Ivanov"),
            new Developer(2L, "Sergey", "Sergeev"),
            new Developer(3L, "Petr", "Petrov")
    ).collect(toList());

    @GetMapping
    public List<Developer> getAll() {
        return DEVELOPERS;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public Developer getById(@PathVariable Long id) {
        return DEVELOPERS.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('developers:write')")
    public Developer create(@RequestBody Developer developer) {
        this.DEVELOPERS.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public void deleteById(@PathVariable Long id) {
        this.DEVELOPERS.removeIf(d -> d.getId().equals(id));
    }
}