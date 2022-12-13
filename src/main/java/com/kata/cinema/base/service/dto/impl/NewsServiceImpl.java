package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.NewsMappers;
import com.kata.cinema.base.models.dto.response.NewsResponseDto;
import com.kata.cinema.base.repositories.NewsRepository;
import com.kata.cinema.base.service.dto.NewsService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableAutoConfiguration
public class NewsServiceImpl implements NewsService {

    private final NewsMappers newsMappers;

    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsMappers newsMappers, NewsRepository newsRepository) {
        this.newsMappers = newsMappers;
        this.newsRepository = newsRepository;
    }

    @Override
    public List<NewsResponseDto> getAllNewsByDate(Long id) {
        return newsMappers.modelsToDTO(newsRepository.findAll(Sort.by(Sort.Direction.DESC,"data")));
    }

    @Override
    public List<NewsResponseDto> getNewsByDate(Long id) {
        return null;
    }
}
