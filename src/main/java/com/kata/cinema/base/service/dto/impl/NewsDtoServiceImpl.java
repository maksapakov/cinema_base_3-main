package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.NewsMappers;
import com.kata.cinema.base.models.dto.response.NewsResponseDto;
import com.kata.cinema.base.models.entity.News;
import com.kata.cinema.base.repositories.NewsRepository;
import com.kata.cinema.base.repositories.RedactorCommentRepository;
import com.kata.cinema.base.service.dto.NewsDtoService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableAutoConfiguration
public class NewsDtoServiceImpl implements NewsDtoService {

    private final NewsMappers newsMappers;

    private final NewsRepository newsRepository;

    private final RedactorCommentRepository redactorCommentRepository;

    public NewsDtoServiceImpl(NewsMappers newsMappers,
                              NewsRepository newsRepository,
                              RedactorCommentRepository redactorCommentRepository) {
        this.newsMappers = newsMappers;
        this.newsRepository = newsRepository;
        this.redactorCommentRepository = redactorCommentRepository;
    }

    //доставать все новости с isModerate=false и status=ACTIVE или null сортировка по дате от самых новых
    @Override
    public List<NewsResponseDto> getAllNewsByIsModerateAndRedactorStatus() {
        return newsMappers.modelsToDTO(redactorCommentRepository.findByNews_IsModerateAndAndRedactorStatus());
    }

    @Override
    public News getNewsById(Long id) {
        return newsMappers.toEntity(newsMappers.toDTO(newsRepository.getById(id)));
    }

    @Override
    public News updateNews(News news) {
        return newsRepository.save(news);
    }
}
