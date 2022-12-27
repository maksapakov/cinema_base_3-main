package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entity.Review;

import java.util.List;

public interface ReviewService {

    void save(Review review);

    List<Review> getAll();

    List<Review> findAllWithFetch();
}
