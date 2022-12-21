package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.News;
import com.kata.cinema.base.models.entity.Review;
import com.kata.cinema.base.repositories.ReviewRepository;
import com.kata.cinema.base.service.entity.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }
}
