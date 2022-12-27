package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.NewsResponseDto;
import com.kata.cinema.base.models.entity.News;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface NewsMappers {

//    NewsMappers INSTANCE = Mappers.getMapper(NewsMappers.class);

    NewsResponseDto toDTO(News news);

    List<NewsResponseDto> modelsToDTO(List<News> news);

    News toEntity(NewsResponseDto newsResponseDto);
}
