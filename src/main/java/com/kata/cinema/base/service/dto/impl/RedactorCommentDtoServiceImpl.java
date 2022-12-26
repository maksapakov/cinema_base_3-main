package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.RedactorCommentMapper;
import com.kata.cinema.base.models.dto.request.RedactorCommentRequestDto;
import com.kata.cinema.base.models.entity.News;
import com.kata.cinema.base.models.entity.RedactorComment;
import com.kata.cinema.base.repositories.NewsRepository;
import com.kata.cinema.base.repositories.RedactorCommentRepository;
import com.kata.cinema.base.service.dto.RedactorCommentDtoService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

@Service
@EnableAutoConfiguration
public class RedactorCommentDtoServiceImpl implements RedactorCommentDtoService {

    private final RedactorCommentMapper redactorCommentMapper;

    private final RedactorCommentRepository redactorCommentRepository;


    public RedactorCommentDtoServiceImpl(RedactorCommentMapper redactorCommentMapper, RedactorCommentRepository redactorCommentRepository) {
        this.redactorCommentMapper = redactorCommentMapper;
        this.redactorCommentRepository = redactorCommentRepository;
    }

    @Override
    public RedactorComment getRedactorCommentByNews_Id(Long id) {

        return redactorCommentRepository.findRedactorCommentByNews_Id(id);
    }
}
