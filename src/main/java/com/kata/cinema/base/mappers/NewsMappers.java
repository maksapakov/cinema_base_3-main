package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.NewsResponseDto;
import com.kata.cinema.base.models.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMappers {

    NewsMappers INSTANCE = Mappers.getMapper(NewsMappers.class);

    NewsResponseDto toDTO(News news);

    List<NewsResponseDto> modelsToDTO(List<News> news);

    News toEntity(NewsResponseDto newsResponseDto);
}
