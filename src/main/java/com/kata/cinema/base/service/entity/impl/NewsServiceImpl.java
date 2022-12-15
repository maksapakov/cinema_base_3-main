package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.News;
import com.kata.cinema.base.repositories.NewsRepository;
import com.kata.cinema.base.service.entity.NewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public void save(News news) {

        newsRepository.save(news);
    }

    @Override
    public List<News> getAll() {
        return newsRepository.findAll();
    }
}
