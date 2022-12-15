package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entity.News;

import java.util.List;

public interface NewsService {

    void save(News news);

    List<News> getAll();
}
