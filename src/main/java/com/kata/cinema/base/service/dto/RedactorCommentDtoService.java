package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.request.RedactorCommentRequestDto;
import com.kata.cinema.base.models.entity.RedactorComment;
import org.springframework.stereotype.Service;

public interface RedactorCommentDtoService {

    RedactorComment getRedactorCommentByNews_Id (Long id);
}
