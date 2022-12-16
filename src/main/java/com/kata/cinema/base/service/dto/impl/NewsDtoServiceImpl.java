package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.NewsMappers;
import com.kata.cinema.base.models.dto.response.NewsResponseDto;
import com.kata.cinema.base.repositories.NewsRepository;
import com.kata.cinema.base.service.dto.NewsDtoService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableAutoConfiguration
public class NewsDtoServiceImpl implements NewsDtoService {

    private final NewsMappers newsMappers;

    private final NewsRepository newsRepository;

    public NewsDtoServiceImpl(NewsMappers newsMappers, NewsRepository newsRepository) {
        this.newsMappers = newsMappers;
        this.newsRepository = newsRepository;
    }

    //доставать все новости с isModerate=false и status=ACTIVE или null сортировка по дате от самых новых
    @Override
    public List<NewsResponseDto> getAllNewsByIsModerateAndRedactorStatus() {
        return newsMappers.modelsToDTO(newsRepository.findListAllNewsByIsModerateAndRedactorStatus());
    }

    @Override
    public List<NewsResponseDto> getNewsById(Long id) {
        return null;
    }
}
