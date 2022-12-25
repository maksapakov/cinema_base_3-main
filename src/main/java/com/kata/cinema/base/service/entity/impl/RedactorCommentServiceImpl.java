package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.RedactorComment;
import com.kata.cinema.base.repositories.RedactorCommentRepository;
import com.kata.cinema.base.service.entity.RedactorCommentService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

@Service
@EnableAutoConfiguration
public class RedactorCommentServiceImpl implements RedactorCommentService {

    private final RedactorCommentRepository redactorCommentRepository;

    public RedactorCommentServiceImpl(RedactorCommentRepository redactorCommentRepository) {
        this.redactorCommentRepository = redactorCommentRepository;
    }

    @Override
    public void save(RedactorComment redactorComment) {
        redactorCommentRepository.save(redactorComment);
    }
}
