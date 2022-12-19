package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.NewsResponseDto;
import com.kata.cinema.base.models.entity.News;

import java.util.List;

public interface NewsDtoService {

    //доставать все новости с isModerate=false и status=ACTIVE или null сортировка по дате от самых новых
    List<NewsResponseDto> getAllNewsByIsModerateAndRedactorStatus();

    News getNewsById(Long id);

    News udateNews(News news);

}
