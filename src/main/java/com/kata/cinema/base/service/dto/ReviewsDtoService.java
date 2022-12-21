package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.entity.Review;

import java.util.List;

public interface ReviewsDtoService {

    //Доставать все ревью с isModerate = false и status = ACTIVE или null сортировка по дате от самых новых
    List<ReviewResponseDto> getAllReviewsByIsModerateAndRedactorStatus();
}
