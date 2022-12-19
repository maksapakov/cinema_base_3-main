package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.request.RedactorCommentRequestDto;
import com.kata.cinema.base.models.entity.RedactorComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RedactorCommentDtoMapper {

    @Mapping(target = "comment", source = "comment")
    @Mapping(target = "redactorStatus", source = "redactorStatus")
    RedactorCommentRequestDto toEntity(RedactorComment redactorComment);

    @Mapping(target = "comment", source = "comment")
    @Mapping(target = "redactorStatus", source = "redactorStatus")
    RedactorComment toDTO(RedactorCommentRequestDto redactorCommentRequestDto);
}
