package com.kata.cinema.base.webapp.controllers.redactor;

import com.kata.cinema.base.models.dto.request.RedactorCommentRequestDto;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.entity.RedactorComment;
import com.kata.cinema.base.models.enums.RedactorStatus;
import com.kata.cinema.base.repositories.RedactorCommentRepository;
import com.kata.cinema.base.repositories.ReviewRepository;
import com.kata.cinema.base.service.dto.ReviewsDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class RedactorReviewsRestController {

    private final ReviewsDtoService reviewsDtoService;
    private final RedactorCommentRepository redactorCommentRepository;
    private final ReviewRepository reviewRepository;

    public RedactorReviewsRestController(ReviewsDtoService reviewsDtoService,
                                         RedactorCommentRepository redactorCommentRepository,
                                         ReviewRepository reviewRepository) {
        this.reviewsDtoService = reviewsDtoService;
        this.redactorCommentRepository = redactorCommentRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/api/redactor/reviews")
    public List<ReviewResponseDto> getReviewsByIsModerateAndRedactorStatus() {
        return reviewsDtoService.getAllReviewsByIsModerateAndRedactorStatus();
    }

    @PostMapping("/api/redactor/reviews/{id}")
    public ResponseEntity<?> updateReviewsIsModerate(@PathVariable Long id,
                                                     @RequestBody RedactorCommentRequestDto redactorCommentRequestDto) {

        RedactorComment redactorComment = redactorCommentRepository.findRedactorCommentByEntity_Id(id);

        if (redactorCommentRequestDto.getRedactorStatus().equals(RedactorStatus.RESOLVED)) {

            redactorComment.setComment(redactorCommentRequestDto.getComment());
            redactorComment.setRedactorStatus(redactorCommentRequestDto.getRedactorStatus());

            redactorComment.getReview().setIsModerate(true);

            reviewRepository.updateIsModerateById(id);

            redactorCommentRepository.save(redactorComment);
        } else {
            redactorComment.setComment(redactorCommentRequestDto.getComment());
            redactorComment.setRedactorStatus(redactorCommentRequestDto.getRedactorStatus());

            redactorCommentRepository.save(redactorComment);

        }

        return ResponseEntity.ok().build();
    }
}
