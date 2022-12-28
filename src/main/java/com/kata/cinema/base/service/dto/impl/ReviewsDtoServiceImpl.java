package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.ReviewMapper;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.repositories.RedactorCommentRepository;
import com.kata.cinema.base.service.dto.ReviewsDtoService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableAutoConfiguration
public class ReviewsDtoServiceImpl implements ReviewsDtoService {

    private final ReviewMapper reviewMapper;
    private final RedactorCommentRepository redactorCommentRepository;

    public ReviewsDtoServiceImpl(ReviewMapper reviewMapper,
                                 RedactorCommentRepository redactorCommentRepository) {
        this.reviewMapper = reviewMapper;
        this.redactorCommentRepository = redactorCommentRepository;
    }

    @Override
    public List<ReviewResponseDto> getAllReviewsByIsModerateAndRedactorStatus() {
        return reviewMapper.modelsToDTO(redactorCommentRepository.findByReviewIsModerateAndRedactorStatus());
    }
}
