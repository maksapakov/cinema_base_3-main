package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.ReviewMapper;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.repositories.ReviewRepository;
import com.kata.cinema.base.service.dto.ReviewsDtoService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableAutoConfiguration
public class ReviewsDtoServiceImpl implements ReviewsDtoService {

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;

    public ReviewsDtoServiceImpl(ReviewMapper reviewMapper, ReviewRepository reviewRepository) {
        this.reviewMapper = reviewMapper;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewResponseDto> getAllReviewsByIsModerateAndRedactorStatus() {
        return reviewMapper.modelsToDTO(reviewRepository.findByIsModerateFalseAndRedactorStatusEqualsOrRedactorStatusNull());
    }
}
