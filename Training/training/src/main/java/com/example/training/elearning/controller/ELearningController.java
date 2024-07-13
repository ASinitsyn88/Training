package com.example.training.elearning.controller;

import com.example.training.elearning.dto.VideoDto;
import com.example.training.elearning.model.Author;
import com.example.training.elearning.service.EAuthorService;
import com.example.training.elearning.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/elearning")
@RequiredArgsConstructor
public class ELearningController {
    private final VideoService videoService;
    private final EAuthorService eAuthorService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody VideoDto videoDto) {
        videoService.save(videoDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/authors/age/{age}")
    public ResponseEntity<List<Author>> getAuthorsByAge(@PathVariable int age) {
        return ResponseEntity.ok().body(eAuthorService.findByAge(age));
    }
}