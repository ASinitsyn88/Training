package com.example.training.elearning.controller;

import com.example.training.elearning.dto.VideoDto;
import com.example.training.elearning.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/elearning")
@RequiredArgsConstructor
public class ELearningController {
    private final VideoService videoService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody VideoDto videoDto) {
        videoService.save(videoDto);
        return ResponseEntity.ok().build();
    }
}