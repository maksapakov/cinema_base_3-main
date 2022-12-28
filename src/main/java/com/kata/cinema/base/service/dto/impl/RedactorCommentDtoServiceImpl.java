package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.entity.RedactorComment;
import com.kata.cinema.base.repositories.RedactorCommentRepository;
import com.kata.cinema.base.service.dto.RedactorCommentDtoService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

@Service
@EnableAutoConfiguration
public class RedactorCommentDtoServiceImpl implements RedactorCommentDtoService {

    private final ThreadLocal<RedactorCommentRepository> redactorCommentRepository = new ThreadLocal<>();


    public RedactorCommentDtoServiceImpl(RedactorCommentRepository redactorCommentRepository) {
        this.redactorCommentRepository.set(redactorCommentRepository);
    }

    @Override
    public RedactorComment getRedactorCommentByNews_Id(Long id) {

        return redactorCommentRepository.get().findRedactorCommentByEntity_Id(id);
    }
}
