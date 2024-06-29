package com.example.training.elearning.service;

import com.example.training.elearning.dto.VideoDto;
import com.example.training.elearning.model.Video;
import com.example.training.elearning.repository.EVideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoService {
    private final EVideoRepository videoRepository;

    @Transactional
    public void save(VideoDto videoDto) {
        log.info("Saving video: {}", videoDto);
        Video video = Video.builder().name(videoDto.name()).length(videoDto.length()).build();
        videoRepository.save(video);
    }
}