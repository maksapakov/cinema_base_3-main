package com.kata.cinema.base.webapp.controllers.redactor;

import com.kata.cinema.base.mappers.RedactorCommentMapper;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.service.dto.ReviewsDtoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class RedactorReviewsRestController {

    private final ReviewsDtoService reviewsDtoService;

    private RedactorCommentMapper redactorCommentMapper;

    public RedactorReviewsRestController(ReviewsDtoService reviewsDtoService, RedactorCommentMapper redactorCommentMapper) {
        this.reviewsDtoService = reviewsDtoService;
        this.redactorCommentMapper = redactorCommentMapper;
    }

    @GetMapping("/api/redactor/reviews")
    public List<ReviewResponseDto> getReviewsByIsModerateAndRedactorStatus() {
        return reviewsDtoService.getAllReviewsByIsModerateAndRedactorStatus();
    }
}
